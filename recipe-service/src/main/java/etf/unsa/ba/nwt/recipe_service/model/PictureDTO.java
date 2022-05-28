package etf.unsa.ba.nwt.recipe_service.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
public class PictureDTO {

    private UUID id;

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
