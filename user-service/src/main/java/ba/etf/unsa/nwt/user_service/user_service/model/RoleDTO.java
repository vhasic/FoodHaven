package ba.etf.unsa.nwt.user_service.user_service.model;

import java.util.UUID;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class RoleDTO {

    private UUID id;
    @NotBlank(message = "Name can't be blank")
    private String name;

    public RoleDTO() {
    }

    public RoleDTO(String name) {
        this.name = name;
    }
}
