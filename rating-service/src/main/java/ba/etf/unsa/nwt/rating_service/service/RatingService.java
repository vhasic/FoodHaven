package ba.etf.unsa.nwt.rating_service.service;

import ba.etf.unsa.nwt.rating_service.domain.Rating;
import ba.etf.unsa.nwt.rating_service.model.RatingDTO;
import ba.etf.unsa.nwt.rating_service.repos.RatingRepository;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class RatingService {

    private final RatingRepository ratingRepository;

    public RatingService(final RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

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
        mapToEntity(ratingDTO, rating);
        return ratingRepository.save(rating).getId();
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

}
