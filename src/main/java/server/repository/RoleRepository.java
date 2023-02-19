package server.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import server.generic.GenericRepository;
import server.model.Role;
import server.model.RoleEnum;
@Repository
public interface RoleRepository  extends GenericRepository<Role>{
    Optional<Role> findByName(RoleEnum name);
}
