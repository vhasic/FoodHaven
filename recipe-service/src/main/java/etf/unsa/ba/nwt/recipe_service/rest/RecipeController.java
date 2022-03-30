package etf.unsa.ba.nwt.recipe_service.rest;

import etf.unsa.ba.nwt.recipe_service.domain.Recipe;
import etf.unsa.ba.nwt.recipe_service.model.RecipeDTO;
import etf.unsa.ba.nwt.recipe_service.service.RecipeService;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/recipes", produces = MediaType.APPLICATION_JSON_VALUE)
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(final RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    public ResponseEntity<List<RecipeDTO>> getAllRecipes() {
        return ResponseEntity.ok(recipeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeDTO> getRecipe(@PathVariable final UUID id) {
        return ResponseEntity.ok(recipeService.get(id));
    }
    @GetMapping("/category")
    public ResponseEntity<List<RecipeDTO>> getRecipesFromCategory(@RequestParam final String categoryId) {
        return ResponseEntity.ok(recipeService.getRecipesFromCategory(UUID.fromString(categoryId)));
    }
    @GetMapping("/user")
    public ResponseEntity<List<RecipeDTO>> getRecipesFromUser(@RequestParam final String userId) {
        return ResponseEntity.ok(recipeService.getRecipesFromUser(UUID.fromString(userId)));
    }

    @PostMapping
    public ResponseEntity<UUID> createRecipe(@RequestBody @Valid final RecipeDTO recipeDTO) {
        return new ResponseEntity<>(recipeService.create(recipeDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateRecipe(@PathVariable final UUID id,
                                               @RequestBody @Valid final RecipeDTO recipeDTO) {
        recipeService.update(id, recipeDTO);
        return ResponseEntity.ok("Successfully updated!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRecipe(@PathVariable final UUID id) {
        recipeService.delete(id);
        return ResponseEntity.ok("Successfully delated!");
    }
    @DeleteMapping
    public ResponseEntity<String> deleteAll() {
        recipeService.deleteAll();
        return ResponseEntity.ok("Successfully deleted!");
    }

}
