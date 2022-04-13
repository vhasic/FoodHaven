package ba.unsa.etf.nwt.ingredient_service.rest;

import ba.unsa.etf.nwt.ingredient_service.model.IngredientDTO;
import ba.unsa.etf.nwt.ingredient_service.service.IngredientService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/ingredients", produces = MediaType.APPLICATION_JSON_VALUE)
public class IngredientController {

    private final IngredientService ingredientService;

    public IngredientController(final IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping
    public ResponseEntity<List<IngredientDTO>> getAllIngredients() {
        return ResponseEntity.ok(ingredientService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngredientDTO> getIngredient(@PathVariable final UUID id) {
        return ResponseEntity.ok(ingredientService.get(id));
    }

    @PostMapping
    public ResponseEntity<UUID> createIngredient(
            @RequestBody @Valid final IngredientDTO ingredientDTO) {
        return new ResponseEntity<>(ingredientService.create(ingredientDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateIngredient(@PathVariable final UUID id,
                                                   @RequestBody @Valid final IngredientDTO ingredientDTO) {
        ingredientService.update(id, ingredientDTO);
        return ResponseEntity.ok("Successfully updated!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteIngredient(@PathVariable final UUID id) {
        ingredientService.delete(id);
        return ResponseEntity.ok("Successfully delated!");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAll() {
        ingredientService.deleteAll();
        return ResponseEntity.ok("Successfully deleted all!");
    }

    @GetMapping(value = "/totalCalories", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getTotalCalories(@RequestParam UUID recipeId) {
        Integer totalCalories = ingredientService.getTotalCalories(recipeId);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("totalCalories", totalCalories);

        return ResponseEntity.ok(map);
    }
}
