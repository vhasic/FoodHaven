package ba.etf.unsa.nwt.user_service.model;

import java.util.UUID;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TokenDTO {

    private UUID id;

    @NotNull
    @Size(max = 255)
    private String token;

    @NotNull
    @Size(max = 255)
    private String type;

    @NotNull
    private Integer duration;

    @NotNull
    private Boolean valid;

    private UUID user;

}
