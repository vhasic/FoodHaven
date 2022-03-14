package etf.unsa.ba.nwt.recipe_service.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RecipeDTO {

    private Integer id;

    @NotNull
    @Size(max = 50)
    private String name;

    @Size(max = 255)
    private String description;

    @NotNull
    private Integer preparationTime;

    @NotNull
    private Integer userID;

    @NotNull
    private Integer recipeCategory;

    @NotNull
    private Integer recipePicture;

}
