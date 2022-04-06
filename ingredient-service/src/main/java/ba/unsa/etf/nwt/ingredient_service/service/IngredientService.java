package ba.unsa.etf.nwt.ingredient_service.service;

import ba.unsa.etf.nwt.ingredient_service.domain.Ingredient;
import ba.unsa.etf.nwt.ingredient_service.domain.Picture;
import ba.unsa.etf.nwt.ingredient_service.model.IngredientDTO;
import ba.unsa.etf.nwt.ingredient_service.repos.IngredientRepository;
import ba.unsa.etf.nwt.ingredient_service.repos.PictureRepository;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.ResponseEntity;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.client.RestTemplate;


@Service
public class IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;
    @Autowired
    private PictureRepository pictureRepository;
    @Autowired
    private RestTemplate restTemplate;
    private final DiscoveryClient discoveryClient;

    @Autowired
    public IngredientService(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    public IngredientService(final IngredientRepository ingredientRepository,
            final PictureRepository pictureRepository,
                             RestTemplate restTemplate, DiscoveryClient discoveryClient) {
        this.ingredientRepository = ingredientRepository;
        this.pictureRepository = pictureRepository;
        this.restTemplate = restTemplate;
        this.discoveryClient = discoveryClient;
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

    public void deleteAll() {
        ingredientRepository.deleteAll();
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

    public Integer getTotalCalories(UUID id) {
        ServiceInstance serviceInstanceRecipe = discoveryClient.getInstances("recipe-service").get(0);
        String resourceURL = serviceInstanceRecipe.getUri() + "/api/recipes/";
        try {
            ResponseEntity<String> response= restTemplate.getForEntity(resourceURL+id, String.class);
            if (response.getStatusCode().equals(HttpStatus.OK)){
                return ingredientRepository.getTotalCalories(id.toString());
            }
        }catch ( Exception e ){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recipe with given id doesn't exist");
        }
        return null;
    }

}
