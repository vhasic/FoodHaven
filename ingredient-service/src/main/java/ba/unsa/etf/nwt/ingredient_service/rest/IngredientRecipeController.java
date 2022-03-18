package ba.unsa.etf.nwt.ingredient_service.rest;

import ba.unsa.etf.nwt.ingredient_service.model.IngredientRecipeDTO;
import ba.unsa.etf.nwt.ingredient_service.service.IngredientRecipeService;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/ingredientRecipes", produces = MediaType.APPLICATION_JSON_VALUE)
public class IngredientRecipeController {

    private final IngredientRecipeService ingredientRecipeService;

    public IngredientRecipeController(final IngredientRecipeService ingredientRecipeService) {
        this.ingredientRecipeService = ingredientRecipeService;
    }

    @GetMapping
    public ResponseEntity<List<IngredientRecipeDTO>> getAllIngredientRecipes() {
        return ResponseEntity.ok(ingredientRecipeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngredientRecipeDTO> getIngredientRecipe(@PathVariable final UUID id) {
        return ResponseEntity.ok(ingredientRecipeService.get(id));
    }

    @PostMapping
    public ResponseEntity<UUID> createIngredientRecipe(
            @RequestBody @Valid final IngredientRecipeDTO ingredientRecipeDTO) {
        return new ResponseEntity<>(ingredientRecipeService.create(ingredientRecipeDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateIngredientRecipe(@PathVariable final UUID id,
            @RequestBody @Valid final IngredientRecipeDTO ingredientRecipeDTO) {
        ingredientRecipeService.update(id, ingredientRecipeDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngredientRecipe(@PathVariable final UUID id) {
        ingredientRecipeService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
