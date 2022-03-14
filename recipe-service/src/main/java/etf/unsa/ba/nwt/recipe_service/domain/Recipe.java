package etf.unsa.ba.nwt.recipe_service.domain;

import java.time.OffsetDateTime;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Recipe {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(name = "\"description\"")
    private String description;

    @Column(nullable = false)
    private Integer preparationTime;

    @Column(nullable = false)
    private Integer userID;

    @OneToOne
    @JoinColumn(name = "recipe_picture_id", nullable = false)
    private Picture recipePicture;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_category_id", nullable = false)
    private Category recipeCategory;

    @OneToMany(mappedBy = "stepRecipe")
    private Set<Step> stepRecipeSteps;

    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

    public Recipe(String name, String description, Integer preparationTime, Integer userID, Picture recipePicture, Category recipeCategory) {
        this.name = name;
        this.description = description;
        this.preparationTime = preparationTime;
        this.userID = userID;
        this.recipePicture = recipePicture;
        this.recipeCategory = recipeCategory;
    }

    public Recipe() {

    }

    @PrePersist
    public void prePersist() {
        dateCreated = OffsetDateTime.now();
        lastUpdated = dateCreated;
    }

    @PreUpdate
    public void preUpdate() {
        lastUpdated = OffsetDateTime.now();
    }

}
