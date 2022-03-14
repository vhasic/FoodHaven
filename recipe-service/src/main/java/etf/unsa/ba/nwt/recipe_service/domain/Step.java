package etf.unsa.ba.nwt.recipe_service.domain;

import java.time.OffsetDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Step {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, name = "\"description\"")
    private String description;

    @OneToOne
    @JoinColumn(name = "step_picture_id", nullable = false)
    private Picture stepPicture;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "step_recipe_id", nullable = false)
    private Recipe stepRecipe;

    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

    public Step(String description, Picture stepPicture, Recipe stepRecipe) {
        this.description = description;
        this.stepPicture = stepPicture;
        this.stepRecipe = stepRecipe;
    }

    public Step() {

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
