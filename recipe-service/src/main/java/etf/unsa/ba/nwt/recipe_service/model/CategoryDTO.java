package etf.unsa.ba.nwt.recipe_service.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CategoryDTO {

    private Integer id;

    @NotNull
    @Size(max = 50)
    private String name;

    @NotNull
    private Integer categoryPicture;

}
