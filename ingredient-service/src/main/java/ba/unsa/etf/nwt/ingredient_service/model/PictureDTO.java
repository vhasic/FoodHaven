package ba.unsa.etf.nwt.ingredient_service.model;

import java.util.UUID;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PictureDTO {

    private UUID id;

    @NotNull(message = "Picture of ingredient is required")
    @Size(max = 255)
    private String picData;

    public PictureDTO() {

    }

    public PictureDTO(String picData) {
        this.picData = picData;
    }
}
