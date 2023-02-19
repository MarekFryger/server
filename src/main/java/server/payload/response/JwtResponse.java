package server.payload.response;
import java.util.List;


import lombok.Data;
import lombok.Generated;
@Data
public class JwtResponse {
    private String token;
	private String type = "Bearer";
	private String id;
	private String username;
	private String email;
	private Boolean active;
	private List<String> roles;

	@Generated
	public JwtResponse(String accessToken, String id, String username, String email, Boolean active, List<String> roles) {
		this.token = accessToken;
		this.id = id;
		this.username = username;
		this.email = email;
		this.active = active;
		this.roles = roles;
	}
}
