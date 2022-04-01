package ba.etf.unsa.nwt.rating_service.rest;

import ba.etf.unsa.nwt.rating_service.model.RatingDTO;
import ba.etf.unsa.nwt.rating_service.service.RatingService;

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
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping(value = "/api/ratings", produces = MediaType.APPLICATION_JSON_VALUE)
public class RatingController {
    @Autowired
    private RatingService ratingService;

    @GetMapping
    public ResponseEntity<List<RatingDTO>> getAllRatings() {
        return ResponseEntity.ok(ratingService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RatingDTO> getRating(@PathVariable final UUID id) {
        return ResponseEntity.ok(ratingService.get(id));
    }

    @PostMapping
    public ResponseEntity<UUID> createRating(@RequestBody @Valid final RatingDTO ratingDTO) {
        return new ResponseEntity<>(ratingService.create(ratingDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateRating(@PathVariable final UUID id,
                                               @RequestBody @Valid final RatingDTO ratingDTO) {
        ratingService.update(id, ratingDTO);
        return ResponseEntity.ok("Successfully updated!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRating(@PathVariable final UUID id) {
        ratingService.delete(id);
        return ResponseEntity.ok("Successfully deleted!");
    }

    @GetMapping("/recipe")
    public ResponseEntity<List<RatingDTO>> getRatingsForRecipe(@RequestParam UUID recipeId) {
        return ResponseEntity.ok(ratingService.getRatingsForRecipe(recipeId));
    }

    @GetMapping("/user")
    public ResponseEntity<List<RatingDTO>> getRatingsForUser(@RequestParam UUID userId) {
        return ResponseEntity.ok(ratingService.getRatingsForUser(userId));
    }

    @GetMapping(value = "/averageRating", produces = MediaType.APPLICATION_JSON_VALUE)
    //@ResponseBody // ovo je po defaultu za klasu @RestController
    public ResponseEntity<Object> getAverageRatingForRecipe(@RequestParam UUID recipeId) {
        Double averageRating=ratingService.getAverageRatingForRecipe(recipeId);
        Integer numberOfRatings=ratingService.getNumberOfRatings(recipeId);

        // custom JSON
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("averageRating", averageRating);
        map.put("numberOfRatings", numberOfRatings);

        return ResponseEntity.ok(map);
    }

}
