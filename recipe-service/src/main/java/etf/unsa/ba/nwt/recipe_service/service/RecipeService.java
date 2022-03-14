package etf.unsa.ba.nwt.recipe_service.service;

import etf.unsa.ba.nwt.recipe_service.domain.Category;
import etf.unsa.ba.nwt.recipe_service.domain.Picture;
import etf.unsa.ba.nwt.recipe_service.domain.Recipe;
import etf.unsa.ba.nwt.recipe_service.model.RecipeDTO;
import etf.unsa.ba.nwt.recipe_service.repos.CategoryRepository;
import etf.unsa.ba.nwt.recipe_service.repos.PictureRepository;
import etf.unsa.ba.nwt.recipe_service.repos.RecipeRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final PictureRepository pictureRepository;
    private final CategoryRepository categoryRepository;

    public RecipeService(final RecipeRepository recipeRepository,
            final PictureRepository pictureRepository,
            final CategoryRepository categoryRepository) {
        this.recipeRepository = recipeRepository;
        this.pictureRepository = pictureRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<RecipeDTO> findAll() {
        return recipeRepository.findAll()
                .stream()
                .map(recipe -> mapToDTO(recipe, new RecipeDTO()))
                .collect(Collectors.toList());
    }

    public RecipeDTO get(final Integer id) {
        return recipeRepository.findById(id)
                .map(recipe -> mapToDTO(recipe, new RecipeDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Integer create(final RecipeDTO recipeDTO) {
        final Recipe recipe = new Recipe();
        mapToEntity(recipeDTO, recipe);
        return recipeRepository.save(recipe).getId();
    }

    public void update(final Integer id, final RecipeDTO recipeDTO) {
        final Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(recipeDTO, recipe);
        recipeRepository.save(recipe);
    }

    public void delete(final Integer id) {
        recipeRepository.deleteById(id);
    }

    private RecipeDTO mapToDTO(final Recipe recipe, final RecipeDTO recipeDTO) {
        recipeDTO.setId(recipe.getId());
        recipeDTO.setName(recipe.getName());
        recipeDTO.setDescription(recipe.getDescription());
        recipeDTO.setPreparationTime(recipe.getPreparationTime());
        recipeDTO.setUserID(recipe.getUserID());
        recipeDTO.setRecipePicture(recipe.getRecipePicture() == null ? null : recipe.getRecipePicture().getId());
        recipeDTO.setRecipeCategory(recipe.getRecipeCategory() == null ? null : recipe.getRecipeCategory().getId());
        return recipeDTO;
    }

    private Recipe mapToEntity(final RecipeDTO recipeDTO, final Recipe recipe) {
        recipe.setName(recipeDTO.getName());
        recipe.setDescription(recipeDTO.getDescription());
        recipe.setPreparationTime(recipeDTO.getPreparationTime());
        recipe.setUserID(recipeDTO.getUserID());
        if (recipeDTO.getRecipePicture() != null && (recipe.getRecipePicture() == null || !recipe.getRecipePicture().getId().equals(recipeDTO.getRecipePicture()))) {
            final Picture recipePicture = pictureRepository.findById(recipeDTO.getRecipePicture())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "recipePicture not found"));
            recipe.setRecipePicture(recipePicture);
        }
        if (recipeDTO.getRecipeCategory() != null && (recipe.getRecipeCategory() == null || !recipe.getRecipeCategory().getId().equals(recipeDTO.getRecipeCategory()))) {
            final Category recipeCategory = categoryRepository.findById(recipeDTO.getRecipeCategory())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "recipeCategory not found"));
            recipe.setRecipeCategory(recipeCategory);
        }
        return recipe;
    }

}
