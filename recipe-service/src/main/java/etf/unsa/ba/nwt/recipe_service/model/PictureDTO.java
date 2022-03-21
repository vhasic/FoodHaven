package etf.unsa.ba.nwt.recipe_service.model;

import java.util.UUID;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PictureDTO {

    private UUID id;

    @NotNull
    @Size(max = 255)
    private String picData;

    public PictureDTO(String picData) {
        this.picData = picData;
    }

    public PictureDTO() {
    }
}
