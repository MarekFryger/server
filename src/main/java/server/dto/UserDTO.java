package server.dto;

import java.util.Set;

import server.model.Role;

public record UserDTO(
                String id,
                String username,
                String email,
                Boolean active,
                Set<Role> roles) {
}
