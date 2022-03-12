package ba.unsa.etf.nwt.rating_service.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
public class ReviewDTO {
    private Integer id;

    @NotNull
    private Integer rating;

    @NotNull
    @Size(max = 255)
    private String comment;

    @NotNull
    private Integer recipeId;

    @NotNull
    private Integer userId;

    public ReviewDTO() {
    }
}
