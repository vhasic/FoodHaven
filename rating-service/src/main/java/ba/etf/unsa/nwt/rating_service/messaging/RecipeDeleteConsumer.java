package ba.etf.unsa.nwt.rating_service.messaging;
import ba.etf.unsa.nwt.rating_service.model.RecipeDTO;
import ba.etf.unsa.nwt.rating_service.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import javax.transaction.Transactional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RecipeDeleteConsumer implements Consumer<RecipeDTO> {

    private final RatingService ratingService;
    private final RecipeDeletePublisher publisher;

    @RabbitListener(queues = "delete-recipe-queue")
    public void receive(RecipeDTO data) {
        try {
            deleteRatings(data.getId());
        } catch (Exception e) {
            publisher.send(data);
        }
    }

    @Transactional
    public void deleteRatings(UUID recipeId) {
        ratingService.deleteRatingsOfRecipe(recipeId);
    }
}
