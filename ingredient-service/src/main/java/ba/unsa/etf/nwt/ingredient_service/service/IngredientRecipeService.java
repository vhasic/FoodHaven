package ba.unsa.etf.nwt.ingredient_service.service;

import ba.unsa.etf.nwt.ingredient_service.domain.Ingredient;
import ba.unsa.etf.nwt.ingredient_service.domain.IngredientRecipe;
import ba.unsa.etf.nwt.ingredient_service.model.IngredientRecipeDTO;
import ba.unsa.etf.nwt.ingredient_service.repos.IngredientRecipeRepository;
import ba.unsa.etf.nwt.ingredient_service.repos.IngredientRepository;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class IngredientRecipeService {

    @Autowired
    private final IngredientRecipeRepository ingredientRecipeRepository;
    @Autowired
    private final IngredientRepository ingredientRepository;

    public IngredientRecipeService(final IngredientRecipeRepository ingredientRecipeRepository,
            final IngredientRepository ingredientRepository) {
        this.ingredientRecipeRepository = ingredientRecipeRepository;
        this.ingredientRepository = ingredientRepository;
    }

    public List<IngredientRecipeDTO> findAll() {
        return ingredientRecipeRepository.findAll()
                .stream()
                .map(ingredientRecipe -> mapToDTO(ingredientRecipe, new IngredientRecipeDTO()))
                .collect(Collectors.toList());
    }

    public IngredientRecipeDTO get(final UUID id) {
        return ingredientRecipeRepository.findById(id)
                .map(ingredientRecipe -> mapToDTO(ingredientRecipe, new IngredientRecipeDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public UUID create(final IngredientRecipeDTO ingredientRecipeDTO) {
        final IngredientRecipe ingredientRecipe = new IngredientRecipe();
        mapToEntity(ingredientRecipeDTO, ingredientRecipe);
        return ingredientRecipeRepository.save(ingredientRecipe).getId();
    }

    public void update(final UUID id, final IngredientRecipeDTO ingredientRecipeDTO) {
        final IngredientRecipe ingredientRecipe = ingredientRecipeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(ingredientRecipeDTO, ingredientRecipe);
        ingredientRecipeRepository.save(ingredientRecipe);
    }

    public void delete(final UUID id) {
        ingredientRecipeRepository.deleteById(id);
    }

    public void deleteAll() {
        ingredientRecipeRepository.deleteAll();
    }

    private IngredientRecipeDTO mapToDTO(final IngredientRecipe ingredientRecipe,
            final IngredientRecipeDTO ingredientRecipeDTO) {
        ingredientRecipeDTO.setId(ingredientRecipe.getId());
        ingredientRecipeDTO.setQuantity(ingredientRecipe.getQuantity());
        ingredientRecipeDTO.setRecipeID(ingredientRecipe.getRecipeID());
        ingredientRecipeDTO.setIngredientRecipeID(ingredientRecipe.getIngredientRecipeID() == null ? null : ingredientRecipe.getIngredientRecipeID().getId());
        return ingredientRecipeDTO;
    }

    private IngredientRecipe mapToEntity(final IngredientRecipeDTO ingredientRecipeDTO,
            final IngredientRecipe ingredientRecipe) {
        ingredientRecipe.setQuantity(ingredientRecipeDTO.getQuantity());
        ingredientRecipe.setRecipeID(ingredientRecipeDTO.getRecipeID());
        if (ingredientRecipeDTO.getIngredientRecipeID() != null && (ingredientRecipe.getIngredientRecipeID() == null || !ingredientRecipe.getIngredientRecipeID().getId().equals(ingredientRecipeDTO.getIngredientRecipeID()))) {
            final Ingredient ingredientRecipeID = ingredientRepository.findById(ingredientRecipeDTO.getIngredientRecipeID())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ingredientRecipeID not found"));
            ingredientRecipe.setIngredientRecipeID(ingredientRecipeID);
        }
        return ingredientRecipe;
    }

}
