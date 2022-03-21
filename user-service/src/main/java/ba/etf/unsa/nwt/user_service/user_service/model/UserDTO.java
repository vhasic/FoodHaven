package ba.etf.unsa.nwt.user_service.user_service.model;

import java.util.UUID;
import javax.validation.constraints.*;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserDTO {

    private UUID id;
    @Size(max = 20, message = "First name can't be longer than twenty characters")
    @NotBlank(message = "First name can't be blank")
    private String firstName;
    @Size(max = 20, message = "Last name can't be longer than twenty characters")
    @NotBlank(message = "Last name can't be blank")
    private String lastName;
    @NotBlank(message = "Username can't be blank")
    private String username;
    @NotBlank(message = "Email can't be blank")
    @Email(message = "Invalid email format")
    private String email;
    @NotBlank(message = "Password can't be blank")
    @Size(min = 8, message = "Password must contain at least eight characters")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=()!?.\"]).{8,}",
            message = "Password must contain at least one lowercase, one uppercase, one digit and one special character")
    private String password;
    private UUID role;

    public UserDTO() {
    }

    public UserDTO(String firstName, String lastName, String username, String email, String password, UUID role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
