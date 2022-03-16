package ba.unsa.etf.nwt.ingredient_service.model;

import javax.validation.constraints.NotNull;


public class IngredientRecipeDTO {

    private Integer id;

    private Integer quantity;

    private Integer recipeID;

    @NotNull
    private Integer ingredientRecipe;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(final Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getRecipeID() {
        return recipeID;
    }

    public void setRecipeID(final Integer recipeID) {
        this.recipeID = recipeID;
    }

    public Integer getIngredientRecipe() {
        return ingredientRecipe;
    }

    public void setIngredientRecipe(final Integer ingredientRecipe) {
        this.ingredientRecipe = ingredientRecipe;
    }

}
