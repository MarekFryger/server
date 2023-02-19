package server.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import server.dto.UserToAdminListDTO;
import server.generic.GenericRepository;
import server.model.User;
@Repository
public interface UserRepository extends GenericRepository<User> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    @Query(nativeQuery = true)
    List<UserToAdminListDTO> getListAll();
}
