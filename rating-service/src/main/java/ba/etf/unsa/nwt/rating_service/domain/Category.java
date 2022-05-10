package ba.etf.unsa.nwt.rating_service.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Category {

    @Id
    @Column(nullable = false, updatable = false, columnDefinition = "char(36)")
    @Type(type = "uuid-char")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid")
    private UUID id;

    @Column(nullable = false, length = 50)
    private String name;

    @OneToMany(mappedBy = "recipeCategory")
    private Set<Recipe> recipeCategoryRecipes;

    @OneToOne
    @JoinColumn(name = "category_picture_id", nullable = false)
    private Picture categoryPicture;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

}
