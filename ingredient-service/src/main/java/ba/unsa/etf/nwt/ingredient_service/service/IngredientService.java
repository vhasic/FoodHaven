package ba.unsa.etf.nwt.ingredient_service.service;

import ba.unsa.etf.nwt.ingredient_service.domain.Ingredient;
import ba.unsa.etf.nwt.ingredient_service.domain.Picture;
import ba.unsa.etf.nwt.ingredient_service.model.IngredientDTO;
import ba.unsa.etf.nwt.ingredient_service.repos.IngredientRepository;
import ba.unsa.etf.nwt.ingredient_service.repos.PictureRepository;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;
    private final PictureRepository pictureRepository;

    public IngredientService(final IngredientRepository ingredientRepository,
            final PictureRepository pictureRepository) {
        this.ingredientRepository = ingredientRepository;
        this.pictureRepository = pictureRepository;
    }

    public List<IngredientDTO> findAll() {
        return ingredientRepository.findAll()
                .stream()
                .map(ingredient -> mapToDTO(ingredient, new IngredientDTO()))
                .collect(Collectors.toList());
    }

    public IngredientDTO get(final UUID id) {
        return ingredientRepository.findById(id)
                .map(ingredient -> mapToDTO(ingredient, new IngredientDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public UUID create(final IngredientDTO ingredientDTO) {
        final Ingredient ingredient = new Ingredient();
        mapToEntity(ingredientDTO, ingredient);
        return ingredientRepository.save(ingredient).getId();
    }

    public void update(final UUID id, final IngredientDTO ingredientDTO) {
        final Ingredient ingredient = ingredientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(ingredientDTO, ingredient);
        ingredientRepository.save(ingredient);
    }

    public void delete(final UUID id) {
        ingredientRepository.deleteById(id);
    }

    private IngredientDTO mapToDTO(final Ingredient ingredient, final IngredientDTO ingredientDTO) {
        ingredientDTO.setId(ingredient.getId());
        ingredientDTO.setName(ingredient.getName());
        ingredientDTO.setCalorieCount(ingredient.getCalorieCount());
        ingredientDTO.setVitamins(ingredient.getVitamins());
        ingredientDTO.setCarbohidrates(ingredient.getCarbohidrates());
        ingredientDTO.setFat(ingredient.getFat());
        ingredientDTO.setProteins(ingredient.getProteins());
        ingredientDTO.setMeasuringUnit(ingredient.getMeasuringUnit());
        ingredientDTO.setIngredientPicture(ingredient.getIngredientPicture() == null ? null : ingredient.getIngredientPicture().getId());
        return ingredientDTO;
    }

    private Ingredient mapToEntity(final IngredientDTO ingredientDTO, final Ingredient ingredient) {
        ingredient.setName(ingredientDTO.getName());
        ingredient.setCalorieCount(ingredientDTO.getCalorieCount());
        ingredient.setVitamins(ingredientDTO.getVitamins());
        ingredient.setCarbohidrates(ingredientDTO.getCarbohidrates());
        ingredient.setFat(ingredientDTO.getFat());
        ingredient.setProteins(ingredientDTO.getProteins());
        ingredient.setMeasuringUnit(ingredientDTO.getMeasuringUnit());
        if (ingredientDTO.getIngredientPicture() != null && (ingredient.getIngredientPicture() == null || !ingredient.getIngredientPicture().getId().equals(ingredientDTO.getIngredientPicture()))) {
            final Picture ingredientPicture = pictureRepository.findById(ingredientDTO.getIngredientPicture())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ingredientPicture not found"));
            ingredient.setIngredientPicture(ingredientPicture);
        }
        return ingredient;
    }

}
