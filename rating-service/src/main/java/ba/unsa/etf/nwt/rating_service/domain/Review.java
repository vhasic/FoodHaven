package ba.unsa.etf.nwt.rating_service.domain;

import java.time.OffsetDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Review {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @Min(value = 1, message = "Rating should be between 1-5")
    @Max(value = 5, message = "Rating should be between 1-5")
    private Integer rating;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    private Integer recipeId;

    @Column(nullable = false)
    private Integer userId;

    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

    @PrePersist
    public void prePersist() {
        dateCreated = OffsetDateTime.now();
        lastUpdated = dateCreated;
    }

    @PreUpdate
    public void preUpdate() {
        lastUpdated = OffsetDateTime.now();
    }


    public Review() {
    }

    public Review(Integer rating, String comment, Integer recipeId, Integer userId) {
        this.rating = rating;
        this.comment = comment;
        this.recipeId = recipeId;
        this.userId = userId;
        this.dateCreated = dateCreated;
        this.lastUpdated = lastUpdated;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                ", recipeId=" + recipeId +
                ", userId=" + userId +
                ", dateCreated=" + dateCreated +
                ", lastUpdated=" + lastUpdated +
                '}';
    }
}
