package ba.unsa.etf.nwt.ingredient_service.model;

import javax.validation.constraints.Size;


public class IngredientDTO {

    private Integer id;

    @Size(max = 255)
    private String name;

    private Integer calorieCount;

    private Integer vitamins;

    private Integer carbohidrates;

    private Integer fat;

    private Integer proteins;

    @Size(max = 50)
    private String measuringUnit;

    private Integer ingredientPicture;

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

    public Integer getIngredientPicture() {
        return ingredientPicture;
    }

    public void setIngredientPicture(final Integer ingredientPicture) {
        this.ingredientPicture = ingredientPicture;
    }

}
