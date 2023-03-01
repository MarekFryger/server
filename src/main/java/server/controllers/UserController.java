package server.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import server.dto.UserDTO;
import server.dto.UserToAdminListDTO;
import server.services.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;

    }

    @GetMapping("/info/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("id") String id) {
        UserDTO user = userService.getUser(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/listAll")
    public ResponseEntity<List<UserToAdminListDTO>> getListAll() {
            List<UserToAdminListDTO> users = userService.getListAll();
            return ResponseEntity.ok(users); 
    }

    @GetMapping("/changeUserActivity")
    public ResponseEntity<UserToAdminListDTO> changeUserActivity(@RequestParam String id) {
        UserToAdminListDTO user = userService.changeUserActivity(id);
        return ResponseEntity.ok(user);
    }

}
