package server.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import server.dto.UserDTO;
import server.dto.UserToAdminListDTO;
import server.mappers.MyMapper;
import server.model.User;
import server.repository.UserRepository;

@SpringBootTest
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;
    @Mock
    private  MyMapper mapper;

    private User user = new User();

    @BeforeEach
    void setUp() {
       
        user.setId("adsasd");
        user.setUsername("testuser");
        user.setEmail("user@gmail.com");
        user.setPassword("****");
        user.setActive(true);
       
    }

    @Test
    void getUserTestFound() {
        when(userRepository.findById("adsasd")).thenReturn(Optional.ofNullable(user));
        UserDTO userDTO = new UserDTO(user.getId(),user.getUsername(),user.getEmail(),user.getActive(),null);
        when(mapper.userToUserDto(user)).thenReturn(userDTO);
        UserDTO result =  userService.getUser("adsasd");
        assertEquals("adsasd", result.id());
        assertEquals("testuser", result.username());
        assertEquals("user@gmail.com", result.email());

    }
    @Test
    void getUserTestNotFound() {
        when(userRepository.findById("adsasd")).thenReturn(Optional.ofNullable(null));
        UserDTO result =  userService.getUser("adsasd");
        assertEquals(null, result);
        

    }
    @Test
    void getListAllTestNoUsers() {
        when(userRepository.getListAll()).thenReturn(List.of());
        assertEquals(0, userService.getListAll().size());
    }
    @Test
    void getListAllTest3Users() {
        when(userRepository.getListAll()).thenReturn(List.of(
            new UserToAdminListDTO("bbb","bbb","two",true),
            new UserToAdminListDTO("aaa","aaa","tree",false),
            new UserToAdminListDTO("aaa","aaa","one",true)
        ));
        List<UserToAdminListDTO> adminListDTOs  =  userService.getListAll();
        assertEquals(3, adminListDTOs.size());
        assertEquals("one", adminListDTOs.get(0).email() );
        assertEquals("two", adminListDTOs.get(1).email() );
        assertEquals("tree", adminListDTOs.get(2).email() );
    }
}
