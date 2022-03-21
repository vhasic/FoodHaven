package ba.etf.unsa.nwt.user_service.user_service.model;

import java.util.UUID;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class TokenDTO {

//    Ovo oko tokena će se naknadno raditi
    private UUID id;
    private String token;
    private String type;
    private Integer duration;
    private Boolean valid=true;
    private UUID user;


    public TokenDTO() {
    }

    public TokenDTO(String token, String type, Integer duration, Boolean valid, UUID user) {
        this.token = token;
        this.type = type;
        this.duration = duration;
        this.valid = valid;
        this.user = user;
    }
}
