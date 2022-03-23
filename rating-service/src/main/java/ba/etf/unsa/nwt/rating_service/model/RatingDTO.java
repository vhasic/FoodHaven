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

    @NotNull(message = "Rating can't be null")
    @Min(value = 1, message = "Rating should be between 1-5")
    @Max(value = 5, message = "Rating should be between 1-5")
    private Integer rating;

    @NotNull(message = "Comment can't be null")
    @Size(max = 255, message = "Comment can't be longer than 255 characters")
    private String comment;

    @NotNull(message = "RecipeId can't be null")
    private UUID recipeId;

    @NotNull(message = "UserId can't be null")
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
