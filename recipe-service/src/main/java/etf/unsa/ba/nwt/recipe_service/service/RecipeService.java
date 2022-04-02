package etf.unsa.ba.nwt.recipe_service.service;

import etf.unsa.ba.nwt.recipe_service.domain.Category;
import etf.unsa.ba.nwt.recipe_service.domain.Picture;
import etf.unsa.ba.nwt.recipe_service.domain.Recipe;
import etf.unsa.ba.nwt.recipe_service.model.RecipeDTO;
import etf.unsa.ba.nwt.recipe_service.repos.CategoryRepository;
import etf.unsa.ba.nwt.recipe_service.repos.PictureRepository;
import etf.unsa.ba.nwt.recipe_service.repos.RecipeRepository;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;


@Service
public class RecipeService {

    @Autowired
    private final RecipeRepository recipeRepository;
    @Autowired
    private final PictureRepository pictureRepository;
    @Autowired
    private final CategoryRepository categoryRepository;
    @Autowired
    private RestTemplate restTemplate;

    private final DiscoveryClient discoveryClient;

    public RecipeService(final RecipeRepository recipeRepository,
            final PictureRepository pictureRepository,
            final CategoryRepository categoryRepository,
                         final DiscoveryClient discoveryClient) {
        this.recipeRepository = recipeRepository;
        this.pictureRepository = pictureRepository;
        this.categoryRepository = categoryRepository;
        this.discoveryClient = discoveryClient;
    }

    public List<RecipeDTO> findAll() {
        return recipeRepository.findAll()
                .stream()
                .map(recipe -> mapToDTO(recipe, new RecipeDTO()))
                .collect(Collectors.toList());
    }

    public RecipeDTO get(final UUID id) {
        return recipeRepository.findById(id)
                .map(recipe -> mapToDTO(recipe, new RecipeDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
    public List<RecipeDTO> getRecipesFromCategory(UUID id) {
        return recipeRepository.getRecipesFromCategory(id.toString())
                .stream()
                .map(recipe -> mapToDTO(recipe, new RecipeDTO()))
                .collect(Collectors.toList());
    }
    public List<RecipeDTO> getRecipesFromUser(UUID id) {
        return recipeRepository.getRecipesFromUser(id.toString())
                .stream()
                .map(recipe -> mapToDTO(recipe, new RecipeDTO()))
                .collect(Collectors.toList());
    }

    public UUID create(final RecipeDTO recipeDTO) {
        final Recipe recipe = new Recipe();
        UUID recipeID = null;
        ServiceInstance serviceInstanceRecipe = discoveryClient.getInstances("user-service").get(0);
        String resourceURL = serviceInstanceRecipe.getUri() + "/api/users/";
        boolean userExist = false;
        try{
            ResponseEntity<String> response= restTemplate.getForEntity(resourceURL+recipeDTO.getUserID(), String.class);
            if (response.getStatusCode().equals(HttpStatus.OK)) {
                userExist = true;

            }
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with given id doesn't exist");
        }
        if(userExist)
        {
            mapToEntity(recipeDTO, recipe);
            recipeID = recipeRepository.save(recipe).getId();
        }
        return recipeID;
    }

    public void update(final UUID id, final RecipeDTO recipeDTO) {
        final Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(recipeDTO, recipe);
        recipeRepository.save(recipe);
    }

    public void delete(final UUID id) {
        recipeRepository.deleteById(id);
    }
    public void deleteAll() {
        recipeRepository.deleteAll();
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
