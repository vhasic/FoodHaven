package ba.unsa.etf.nwt.ingredient_service.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class IngredientRecipe {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer quantity;

    @Column
    private Integer recipeID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_recipe_id", nullable = false)
    private Ingredient ingredientRecipe;

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

    public Ingredient getIngredientRecipe() {
        return ingredientRecipe;
    }

    public void setIngredientRecipe(final Ingredient ingredientRecipe) {
        this.ingredientRecipe = ingredientRecipe;
    }

    public IngredientRecipe(Integer quantity, Integer recipeID, Ingredient ingredientRecipe) {
        this.quantity = quantity;
        this.recipeID = recipeID;
        this.ingredientRecipe = ingredientRecipe;
    }

    public IngredientRecipe() {

    }
}
