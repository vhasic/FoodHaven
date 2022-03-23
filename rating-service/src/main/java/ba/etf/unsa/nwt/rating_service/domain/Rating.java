package ba.etf.unsa.nwt.rating_service.domain;

import java.time.OffsetDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
public class Rating {

    @Id
    @Column(nullable = false, updatable = false, columnDefinition = "char(36)")
    @Type(type = "uuid-char")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid")
    private UUID id;

    @Column(nullable = false)
    @NotNull(message = "Rating can't be null")
    @Min(value = 1, message = "Rating should be between 1-5")
    @Max(value = 5, message = "Rating should be between 1-5")
    private Integer rating;

    @Column(nullable = false)
    @NotNull(message = "Comment can't be null")
    @Size(max = 255, message = "Comment can't be longer than 255 characters")
    private String comment;

    @Column(nullable = false, columnDefinition = "char(36)")
    @Type(type = "uuid-char")
    @NotNull(message = "RecipeId can't be null")
    private UUID recipeId;

    @Column(nullable = false, columnDefinition = "char(36)")
    @Type(type = "uuid-char")
    @NotNull(message = "UserId can't be null")
    private UUID userId;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

}
