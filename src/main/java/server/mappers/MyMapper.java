package server.mappers;

import org.mapstruct.Mapper;
import server.dto.UserDTO;
import server.dto.UserToAdminListDTO;
import server.model.User;

@Mapper
public interface MyMapper {
   UserDTO userToUserDto(User user);
   User dtoToUser(UserDTO userDTO);

   UserToAdminListDTO userToUserAdminListDto (User user);
}
