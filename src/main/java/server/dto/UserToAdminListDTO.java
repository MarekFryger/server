package server.dto;

public record UserToAdminListDTO(
                String id,
                String username,
                String email,
                Boolean active) {
}
