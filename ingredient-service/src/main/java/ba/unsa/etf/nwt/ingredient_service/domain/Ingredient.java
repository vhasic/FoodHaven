package ba.unsa.etf.nwt.ingredient_service.domain;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class Ingredient {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private Integer calorieCount;

    @Column
    private Integer vitamins;

    @Column
    private Integer carbohidrates;

    @Column
    private Integer fat;

    @Column
    private Integer proteins;

    @Column(length = 50)
    private String measuringUnit;

    @OneToOne
    @JoinColumn(name = "ingredient_picture_id")
    private Picture ingredientPicture;

    @OneToMany(mappedBy = "ingredientRecipe")
    private Set<IngredientRecipe> ingredientRecipeIngredientRecipes;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Integer getCalorieCount() {
        return calorieCount;
    }

    public void setCalorieCount(final Integer calorieCount) {
        this.calorieCount = calorieCount;
    }

    public Integer getVitamins() {
        return vitamins;
    }

    public void setVitamins(final Integer vitamins) {
        this.vitamins = vitamins;
    }

    public Integer getCarbohidrates() {
        return carbohidrates;
    }

    public void setCarbohidrates(final Integer carbohidrates) {
        this.carbohidrates = carbohidrates;
    }

    public Integer getFat() {
        return fat;
    }

    public void setFat(final Integer fat) {
        this.fat = fat;
    }

    public Integer getProteins() {
        return proteins;
    }

    public void setProteins(final Integer proteins) {
        this.proteins = proteins;
    }

    public String getMeasuringUnit() {
        return measuringUnit;
    }

    public void setMeasuringUnit(final String measuringUnit) {
        this.measuringUnit = measuringUnit;
    }

    public Picture getIngredientPicture() {
        return ingredientPicture;
    }

    public void setIngredientPicture(final Picture ingredientPicture) {
        this.ingredientPicture = ingredientPicture;
    }

    public Set<IngredientRecipe> getIngredientRecipeIngredientRecipes() {
        return ingredientRecipeIngredientRecipes;
    }

    public void setIngredientRecipeIngredientRecipes(
            final Set<IngredientRecipe> ingredientRecipeIngredientRecipes) {
        this.ingredientRecipeIngredientRecipes = ingredientRecipeIngredientRecipes;
    }

    public Ingredient(String name, Integer calorieCount, Integer vitamins, Integer carbohidrates,
                      Integer fat, Integer proteins, String measuringUnit, Picture ingredientPicture) {
        this.name = name;
        this.calorieCount = calorieCount;
        this.vitamins = vitamins;
        this.carbohidrates = carbohidrates;
        this.fat = fat;
        this.proteins = proteins;
        this.measuringUnit = measuringUnit;
        this.ingredientPicture = ingredientPicture;
    }

    public Ingredient() {

    }

}
