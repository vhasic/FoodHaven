package etf.unsa.ba.nwt.recipe_service.model;

import java.util.UUID;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PictureDTO {

    private UUID id;

    /*@NotNull
    @Size(max = 255)
    private String picData;
    */

    private String name;
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
