package ba.etf.unsa.nwt.rating_service.model;

import ba.etf.unsa.nwt.rating_service.domain.Recipe;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

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

    public RecipeDTO(Recipe recipe) {
        this.id = recipe.getId();
        this.name = recipe.getName();
        this.description = recipe.getDescription();
        this.preparationTime = recipe.getPreparationTime();
        this.userID = recipe.getUserID();
        this.recipePicture = recipe.getRecipePicture().getId();
        this.recipeCategory = recipe.getRecipeCategory().getId();
    }
}

