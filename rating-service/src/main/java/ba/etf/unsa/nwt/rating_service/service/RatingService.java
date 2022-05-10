package ba.etf.unsa.nwt.rating_service.service;

import ba.etf.unsa.nwt.rating_service.domain.Rating;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import ba.etf.unsa.nwt.rating_service.model.RatingDTO;
import ba.etf.unsa.nwt.rating_service.repos.RatingRepository;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;


@Service
public class RatingService {
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private RestTemplate restTemplate;
    private DiscoveryClient discoveryClient;

    @Autowired
    public RatingService(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    public RatingService(RatingRepository ratingRepository, RestTemplate restTemplate, DiscoveryClient discoveryClient) {
        this.ratingRepository = ratingRepository;
        this.restTemplate = restTemplate;
        this.discoveryClient = discoveryClient;
    }
    public void setDiscoveryClient(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    //    public RatingService(final RatingRepository ratingRepository) {
//        this.ratingRepository = ratingRepository;
//    }

    public List<RatingDTO> findAll() {
        return ratingRepository.findAll()
                .stream()
                .map(rating -> mapToDTO(rating, new RatingDTO()))
                .collect(Collectors.toList());
    }

    public RatingDTO get(final UUID id) {
        return ratingRepository.findById(id)
                .map(rating -> mapToDTO(rating, new RatingDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public UUID create(final RatingDTO ratingDTO) {
        final Rating rating = new Rating();
        UUID id=null;
        ServiceInstance serviceInstanceRecipe = discoveryClient.getInstances("recipe-service").get(0);
        String resourceURL = serviceInstanceRecipe.getUri() + "/api/recipes/";
        try {
            ResponseEntity<String> response= restTemplate.getForEntity(resourceURL+ratingDTO.getRecipeId(), String.class);
            if (response.getStatusCode().equals(HttpStatus.OK)){
                mapToEntity(ratingDTO, rating);
                id=ratingRepository.save(rating).getId();
            }
        }catch ( Exception e ){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recipe with given id doesn't exist");
        }

        return id;
//        return ratingRepository.save(rating).getId();
    }

    public void update(final UUID id, final RatingDTO ratingDTO) {
        final Rating rating = ratingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(ratingDTO, rating);
        ratingRepository.save(rating);
    }

    public void delete(final UUID id) {
        ratingRepository.deleteById(id);
    }

    private RatingDTO mapToDTO(final Rating rating, final RatingDTO ratingDTO) {
        ratingDTO.setId(rating.getId());
        ratingDTO.setRating(rating.getRating());
        ratingDTO.setComment(rating.getComment());
        ratingDTO.setRecipeId(rating.getRecipeId());
        ratingDTO.setUserId(rating.getUserId());
        return ratingDTO;
    }

    private Rating mapToEntity(final RatingDTO ratingDTO, final Rating rating) {
        rating.setRating(ratingDTO.getRating());
        rating.setComment(ratingDTO.getComment());
        rating.setRecipeId(ratingDTO.getRecipeId());
        rating.setUserId(ratingDTO.getUserId());
        return rating;
    }

    public Integer getNumberOfRatings(UUID recipeId) {
        return ratingRepository.countByRecipeId(recipeId);
    }

    public Double getAverageRatingForRecipe(final UUID recipeId){
        return ratingRepository.getAverageRatingByRecipeId(recipeId.toString());
    }

    public List<RatingDTO> getRatingsForUser(final UUID userId){
        return ratingRepository.getAllByUserId(userId)
                .stream()
                .map(rating -> mapToDTO(rating, new RatingDTO()))
                .collect(Collectors.toList());
    }

    public List<RatingDTO> getRatingsForRecipe(final UUID recepeId){
        return ratingRepository.getAllByRecipeId(recepeId)
                .stream()
                .map(rating -> mapToDTO(rating, new RatingDTO()))
                .collect(Collectors.toList());
    }

    public void deleteRatingsOfRecipe(UUID recipeId) {
        ratingRepository.deleteAllByRecipeId(recipeId.toString());
    }
}
