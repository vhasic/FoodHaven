package etf.unsa.ba.nwt.recipe_service.rest;

import etf.unsa.ba.nwt.recipe_service.model.RecipeDTO;
import etf.unsa.ba.nwt.recipe_service.model.StepDTO;
import etf.unsa.ba.nwt.recipe_service.service.StepService;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/api/steps", produces = MediaType.APPLICATION_JSON_VALUE)
public class StepController {

    private final StepService stepService;

    public StepController(final StepService stepService) {
        this.stepService = stepService;
    }

    @GetMapping
    public ResponseEntity<List<StepDTO>> getAllSteps() {
        return ResponseEntity.ok(stepService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StepDTO> getStep(@PathVariable final UUID id) {
        return ResponseEntity.ok(stepService.get(id));
    }
    @GetMapping("/recipe")
    public ResponseEntity<List<StepDTO>> getStepsForRecipe(@RequestParam UUID recipeId) {
        return ResponseEntity.ok(stepService.getStepsForRecipe(recipeId));
    }
    @GetMapping("/number")
    public ResponseEntity<StepDTO> getStepXForRecipe(@RequestParam final String recipeId, @RequestParam final Integer number) {
        return ResponseEntity.ok(stepService.getStepXForRecipe(UUID.fromString(recipeId), number));
    }
    @PostMapping
    public ResponseEntity<UUID> createStep(@RequestBody @Valid final StepDTO stepDTO) {
        return new ResponseEntity<>(stepService.create(stepDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateStep(@PathVariable final UUID id,
                                             @RequestBody @Valid final StepDTO stepDTO) {
        stepService.update(id, stepDTO);
        return ResponseEntity.ok("Successfully updated!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStep(@PathVariable final UUID id) {
        stepService.delete(id);
        return ResponseEntity.ok("Successfully deleted!");
    }
    @DeleteMapping
    public ResponseEntity<String> deleteAll() {
        stepService.deleteAll();
        return ResponseEntity.ok("Successfully deleted!");
    }

}
