package server.services;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import server.dto.UserDTO;
import server.dto.UserToAdminListDTO;
import server.mappers.MyMapper;
import server.model.User;
import server.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final MyMapper mapper;

    @Autowired
    public UserService(UserRepository userRepository, MyMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    public UserDTO getUser(String id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(mapper::userToUserDto).orElse(null);
    }

    public List<UserToAdminListDTO> getListAll() {
        return userRepository.getListAll().parallelStream()
                .sorted(Comparator.comparing(UserToAdminListDTO::active)
                .reversed()
                .thenComparing(UserToAdminListDTO::username))
                .toList();
    }

    public UserToAdminListDTO changeUserActivity(String id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            user.get().setActive(!user.get().getActive());
            User userToSave = user.get();
            return mapper.userToUserAdminListDto(userRepository.save(userToSave));
        } else {
            throw new NoSuchElementException("User not found");
        }
    }
}
