package etf.unsa.ba.nwt.recipe_service.domain;

import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Recipe {

    @Id
    @Column(nullable = false, updatable = false, columnDefinition = "char(36)")
    @Type(type = "uuid-char")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid")
    private UUID id;

    @Column(nullable = false, length = 50)
    @NotEmpty(message = "Recipe name is required!")
    @Size(max = 50, message = "Recipe name can't be longer than 50 characters!")
    private String name;

    @Column(name = "\"description\"")
    @NotNull
    @Digits(message="Preparation time (in minutes) must be an integer.", fraction = 0, integer = 4)
    @Size(max = 255, message = "Recipe description can't be longer than 255 characters!")
    private String description;

    @Column(nullable = false)

    private Integer preparationTime;

    @Column(nullable = false)
    private UUID userID;

    @OneToOne
    @JoinColumn(name = "recipe_picture_id", nullable = false)
    private Picture recipePicture;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_category_id", nullable = false)
    private Category recipeCategory;

    @OneToMany(mappedBy = "stepRecipe")
    private Set<Step> stepRecipeSteps;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

}
