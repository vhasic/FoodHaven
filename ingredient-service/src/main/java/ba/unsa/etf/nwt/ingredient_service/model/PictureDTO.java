package ba.unsa.etf.nwt.ingredient_service.model;

import java.util.UUID;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PictureDTO {

    private UUID id;

    @Size(max = 255)
    private String picData;

    public PictureDTO() {

    }

    public PictureDTO(String picData) {
        this.picData = picData;
    }
}
