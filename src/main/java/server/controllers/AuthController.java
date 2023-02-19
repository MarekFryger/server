package server.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import server.model.Role;
import server.model.RoleEnum;
import server.model.User;
import server.payload.request.LoginRequest;
import server.payload.request.SignupRequest;
import server.payload.response.JwtResponse;
import server.payload.response.MessageResponse;
import server.repository.RoleRepository;
import server.repository.UserRepository;
import server.security.jwt.JwtUtils;
import server.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("/signin")
  public ResponseEntity<Object> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    List<String> roles = userDetails.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .toList();

        if(userDetails.getActive() == null || !userDetails.getActive()){
          return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Your acount is not active"));
        }else{
          return ResponseEntity.ok(new JwtResponse(jwt,
          userDetails.getId(),
          userDetails.getUsername(),
          userDetails.getEmail(),
          userDetails.getActive(),
          roles));
        }
  }

  @PostMapping("/signup")
  public ResponseEntity<Object> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (Boolean.TRUE.equals(userRepository.existsByUsername(signUpRequest.getUsername()))) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Email is already in use!"));
    }

    // Create new user's account
    User user = new User(signUpRequest.getUsername(),
        signUpRequest.getEmail(),
        encoder.encode(signUpRequest.getPassword()));

    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    Supplier<? extends RuntimeException> exceptionSupplier = () -> new RuntimeException("Error: Role is not found.");
    if (strRoles == null) {
      Role userRole = roleRepository.findByName(RoleEnum.ROLE_USER)
          .orElseThrow(exceptionSupplier);
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
          case "admin":
            Role adminRole = roleRepository.findByName(RoleEnum.ROLE_ADMIN)
                .orElseThrow(exceptionSupplier);
            roles.add(adminRole);

            break;
          case "mod":
            Role modRole = roleRepository.findByName(RoleEnum.ROLE_MODERATOR)
                .orElseThrow(exceptionSupplier);
            roles.add(modRole);

            break;
          default:
            Role userRole = roleRepository.findByName(RoleEnum.ROLE_USER)
                .orElseThrow(exceptionSupplier);
            roles.add(userRole);
        }
      });
    }

    user.setRoles(roles);
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }
}
