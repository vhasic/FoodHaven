package ba.etf.unsa.nwt.rating_service.messaging;

import ba.etf.unsa.nwt.rating_service.config.RabbitMQConfig;
import ba.etf.unsa.nwt.rating_service.model.RecipeDTO;
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
                RabbitMQConfig.RevertRecipeDeleteQueueConfig.EXCHANGE_NAME,
                RabbitMQConfig.RevertRecipeDeleteQueueConfig.ROUTING_KEY,
                data
        );
    }
}

