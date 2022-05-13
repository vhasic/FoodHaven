package ba.etf.unsa.nwt.user_service.user_service.model;

import lombok.Data;

import java.util.UUID;

@Data
public class UserToReturn {
    private UUID id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private UUID role;

    public UserToReturn(UserDTO userDTO) {
        id=userDTO.getId();
        firstName=userDTO.getFirstName();
        lastName=userDTO.getLastName();
        username=userDTO.getUsername();
        email=userDTO.getEmail();
        role=userDTO.getRole();
    }
}
