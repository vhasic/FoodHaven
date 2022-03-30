package ba.unsa.etf.nwt.ingredient_service.model;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;


@Getter
@Setter
public class PictureDTO {

    private UUID id;

    @NotEmpty(message = "Picture name is required")
    private String name;

    @NotEmpty(message = "Picture type is required")
    private String type;
    private byte[] picByte;

    public PictureDTO(String name, String type, byte[] picByte) {
        this.name = name;
        this.type = type;
        this.picByte = picByte;
    }

    public PictureDTO() {

    }
}
