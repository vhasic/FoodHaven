package etf.unsa.ba.nwt.recipe_service.model;

import java.util.UUID;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RecipeDTO {

    private UUID id;

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
    private UUID recipePicture;

    @NotNull
    private UUID recipeCategory;

    public RecipeDTO() {
    }

    public RecipeDTO(String name, String description, Integer preparationTime, Integer userID, UUID recipePicture, UUID recipeCategory) {
        this.name = name;
        this.description = description;
        this.preparationTime = preparationTime;
        this.userID = userID;
        this.recipePicture = recipePicture;
        this.recipeCategory = recipeCategory;
    }
}
