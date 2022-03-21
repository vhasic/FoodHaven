package etf.unsa.ba.nwt.recipe_service.model;

import java.util.UUID;
import javax.validation.constraints.*;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RecipeDTO {

    private UUID id;

    @NotEmpty(message = "Recipe name is required!")
    @Size(max = 50, message = "Recipe name can't be longer than 50 characters!")
    private String name;

    @Size(max = 255, message = "Recipe description can't be longer than 255 characters!")
    private String description;

    @NotNull(message="Can't be null!")
    private Integer preparationTime;

    @NotNull
    private UUID userID;

    @NotNull
    private UUID recipePicture;

    @NotNull
    private UUID recipeCategory;

    public RecipeDTO() {
    }

    public RecipeDTO(String name, String description, Integer preparationTime, UUID userID, UUID recipePicture, UUID recipeCategory) {
        this.name = name;
        this.description = description;
        this.preparationTime = preparationTime;
        this.userID = userID;
        this.recipePicture = recipePicture;
        this.recipeCategory = recipeCategory;
    }
}
