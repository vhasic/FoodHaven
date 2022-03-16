package ba.unsa.etf.nwt.ingredient_service.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class Picture {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String picData;

    @OneToOne(
            mappedBy = "ingredientPicture",
            fetch = FetchType.LAZY
    )
    private Ingredient ingredientPicture;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getPicData() {
        return picData;
    }

    public void setPicData(final String picData) {
        this.picData = picData;
    }

    public Ingredient getIngredientPicture() {
        return ingredientPicture;
    }

    public void setIngredientPicture(final Ingredient ingredientPicture) {
        this.ingredientPicture = ingredientPicture;
    }

    public Picture(String picData) {
        this.picData = picData;
    }

    public Picture() {

    }
}
