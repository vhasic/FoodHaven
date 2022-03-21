package etf.unsa.ba.nwt.recipe_service.model;

import java.util.UUID;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class StepDTO {

    private UUID id;

    @NotBlank(message = "Step description may not be blank!")
    @Size(max = 255, message = "Step description can't be longer than 255 characters!")
    private String description;

    @NotNull
    private UUID stepPicture;

    @NotNull
    private UUID stepRecipe;

    @NotNull
    private Integer oNumber;

    public StepDTO(String description, Integer oNumber, UUID stepPicture, UUID stepRecipe) {
        this.description = description;
        this.oNumber = oNumber;
        this.stepPicture = stepPicture;
        this.stepRecipe = stepRecipe;
    }

    public StepDTO() {
    }
}
