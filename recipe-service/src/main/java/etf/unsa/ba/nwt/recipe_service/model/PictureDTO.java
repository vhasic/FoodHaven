package etf.unsa.ba.nwt.recipe_service.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PictureDTO {

    private Integer id;

    @NotNull
    @Size(max = 255)
    private String picData;

}
