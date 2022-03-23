package ba.etf.unsa.nwt.rating_service.rest;

import ba.etf.unsa.nwt.rating_service.model.RatingDTO;
import ba.etf.unsa.nwt.rating_service.service.RatingService;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/ratings", produces = MediaType.APPLICATION_JSON_VALUE)
public class RatingController {
    @Autowired
    private RatingService ratingService;

//    public RatingController(final RatingService ratingService) {
//        this.ratingService = ratingService;
//    }

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
    public ResponseEntity<Void> updateRating(@PathVariable final UUID id,
            @RequestBody @Valid final RatingDTO ratingDTO) {
        ratingService.update(id, ratingDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRating(@PathVariable final UUID id) {
        ratingService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/recipe")
    public ResponseEntity<List<RatingDTO>> getRatingsForRecipe(@RequestParam UUID recipeId) {
        return ResponseEntity.ok(ratingService.getRatingsForRecipe(recipeId));
    }

    @GetMapping("/user")
    public ResponseEntity<List<RatingDTO>> getRatingsForUser(@RequestParam UUID userId) {
        return ResponseEntity.ok(ratingService.getRatingsForUser(userId));
    }

    @GetMapping("/averageRating")
    //@ResponseBody // ovo je po defaultu za klasu @RestController
    public Double getAverageRatingForRecipe(@RequestParam UUID recipeId) {
        //todo upit u repositoriju za ovo pravi problem
        double averageRating= ratingService.getAverageRatingForRecipe(recipeId);
        return averageRating;
    }

}
