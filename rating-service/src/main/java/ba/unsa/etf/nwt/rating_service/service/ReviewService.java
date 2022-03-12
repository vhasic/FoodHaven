package ba.unsa.etf.nwt.rating_service.service;

import ba.unsa.etf.nwt.rating_service.domain.Review;
import ba.unsa.etf.nwt.rating_service.model.ReviewDTO;
import ba.unsa.etf.nwt.rating_service.repos.ReviewRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewService(final ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<ReviewDTO> findAll() {
        return reviewRepository.findAll()
                .stream()
                .map(review -> mapToDTO(review, new ReviewDTO()))
                .collect(Collectors.toList());
    }

    public ReviewDTO get(final Integer id) {
        return reviewRepository.findById(id)
                .map(review -> mapToDTO(review, new ReviewDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Integer create(final ReviewDTO reviewDTO) {
        final Review review = new Review();
        mapToEntity(reviewDTO, review);
        return reviewRepository.save(review).getId();
    }

    public void update(final Integer id, final ReviewDTO reviewDTO) {
        final Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(reviewDTO, review);
        reviewRepository.save(review);
    }

    public void delete(final Integer id) {
        reviewRepository.deleteById(id);
    }

    private ReviewDTO mapToDTO(final Review review, final ReviewDTO reviewDTO) {
        reviewDTO.setId(review.getId());
        reviewDTO.setRating(review.getRating());
        reviewDTO.setComment(review.getComment());
        reviewDTO.setRecipeId(review.getRecipeId());
        reviewDTO.setUserId(review.getUserId());
        return reviewDTO;
    }

    private Review mapToEntity(final ReviewDTO reviewDTO, final Review review) {
        review.setRating(reviewDTO.getRating());
        review.setComment(reviewDTO.getComment());
        review.setRecipeId(reviewDTO.getRecipeId());
        review.setUserId(reviewDTO.getUserId());
        return review;
    }

}
