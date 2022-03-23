package ba.unsa.etf.nwt.ingredient_service.rest;

import ba.unsa.etf.nwt.ingredient_service.model.PictureDTO;
import ba.unsa.etf.nwt.ingredient_service.service.PictureService;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(value = "/api/ingredientPictures", produces = MediaType.APPLICATION_JSON_VALUE)
public class PictureController {

    @Autowired
    private final PictureService pictureService;

    public PictureController(final PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @GetMapping
    public ResponseEntity<List<PictureDTO>> getAllPictures() {
        return ResponseEntity.ok(pictureService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PictureDTO> getPicture(@PathVariable final UUID id) {
        return ResponseEntity.ok(pictureService.get(id));
    }

    @PostMapping
    public ResponseEntity<UUID> createPicture(@RequestBody @Valid final PictureDTO pictureDTO) {
        return new ResponseEntity<>(pictureService.create(pictureDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePicture(@PathVariable final UUID id,
            @RequestBody @Valid final PictureDTO pictureDTO) {
        pictureService.update(id, pictureDTO);
        return ResponseEntity.ok("Successfully updated!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePicture(@PathVariable final UUID id) {
        pictureService.delete(id);
        return ResponseEntity.ok("Successfully deleted!");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAll() {
        pictureService.deleteAll();
        return ResponseEntity.ok("Successfully deleted all!");
    }

}
