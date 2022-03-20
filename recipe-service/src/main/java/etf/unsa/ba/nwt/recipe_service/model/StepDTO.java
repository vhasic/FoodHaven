package etf.unsa.ba.nwt.recipe_service.model;

import java.util.UUID;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
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

    public StepDTO() {
    }

    public StepDTO(String description, UUID stepPicture, UUID stepRecipe) {
        this.id = id;
        this.description = description;
        this.stepPicture = stepPicture;
        this.stepRecipe = stepRecipe;
    }
}
