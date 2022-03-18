package ba.etf.unsa.nwt.rating_service.model;

import java.util.UUID;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RatingDTO {

    private UUID id;

    @NotNull
    @Min(value = 1, message = "Rating should be between 1-5")
    @Max(value = 5, message = "Rating should be between 1-5")
    private Integer rating;

    @NotNull
    @Size(max = 255)
    private String comment;

    @NotNull
    private UUID recipeId;

    @NotNull
    private UUID userId;

    public RatingDTO() {
    }

    public RatingDTO(Integer rating, String comment, UUID recipeId, UUID userId) {
        this.rating = rating;
        this.comment = comment;
        this.recipeId = recipeId;
        this.userId = userId;
    }
}
