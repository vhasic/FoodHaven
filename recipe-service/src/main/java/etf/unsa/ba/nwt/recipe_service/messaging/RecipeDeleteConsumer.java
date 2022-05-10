package etf.unsa.ba.nwt.recipe_service.messaging;

import etf.unsa.ba.nwt.recipe_service.model.RecipeDTO;
import etf.unsa.ba.nwt.recipe_service.repos.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RecipeDeleteConsumer implements Consumer<RecipeDTO> {

    private final RecipeRepository recipeRepository;

    @RabbitListener(queues = "revert-recipe-delete-queue")
    public void receive(RecipeDTO data) {
        recipeRepository.find(data.getId()).ifPresent(r -> {
            r.setDeleted(false);
            recipeRepository.save(r);
        });
    }
}
