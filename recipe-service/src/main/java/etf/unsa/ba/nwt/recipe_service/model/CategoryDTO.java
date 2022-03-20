package etf.unsa.ba.nwt.recipe_service.model;

import java.util.UUID;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CategoryDTO {

    private UUID id;

    @NotNull
    @Size(max = 50)
    private String name;

    public CategoryDTO() {
    }

    @NotNull
    private UUID categoryPicture;

    public CategoryDTO(String name, UUID categoryPicture) {
        this.name = name;
        this.categoryPicture = categoryPicture;
    }
}
