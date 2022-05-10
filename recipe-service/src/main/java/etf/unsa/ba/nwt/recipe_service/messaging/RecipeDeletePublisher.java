package etf.unsa.ba.nwt.recipe_service.messaging;

import etf.unsa.ba.nwt.recipe_service.config.RabbitMQConfig;
import etf.unsa.ba.nwt.recipe_service.model.RecipeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RecipeDeletePublisher implements Publisher<RecipeDTO> {

    private final RabbitTemplate template;

    @Override
    public void send(RecipeDTO data) {
        this.template.convertAndSend(
                RabbitMQConfig.DeleteRecipeQueueConfig.EXCHANGE_NAME,
                RabbitMQConfig.DeleteRecipeQueueConfig.ROUTING_KEY,
                data
        );
    }
}
