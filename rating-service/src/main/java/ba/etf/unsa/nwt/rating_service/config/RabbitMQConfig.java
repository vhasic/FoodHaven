package ba.etf.unsa.nwt.rating_service.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Configuration
    public static class RevertRecipeDeleteQueueConfig {

        public static final String QUEUE_NAME = "revert-recipe-delete-queue";
        public static final String EXCHANGE_NAME = "revert-recipe-delete-exchange";
        public static final String ROUTING_KEY = "revert-recipe-delete-routing-key";

        @Bean
        public Queue revertRecipeDeleteQueue() {
            return new Queue(QUEUE_NAME);
        }

        @Bean
        public DirectExchange revertRecipeDeleteExchange() {
            return new DirectExchange(EXCHANGE_NAME);
        }

        @Bean
        public Binding revertRecipeDeleteBinding(Queue revertRecipeDeleteQueue, DirectExchange revertRecipeDeleteExchange) {
            return BindingBuilder
                    .bind(revertRecipeDeleteQueue)
                    .to(revertRecipeDeleteExchange)
                    .with(ROUTING_KEY);
        }
    }

    @Bean
    public MessageConverter messageConverter() {
        ObjectMapper mapper = new ObjectMapper()
                .findAndRegisterModules()
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return new Jackson2JsonMessageConverter(mapper);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory factory, MessageConverter converter) {
        RabbitTemplate template = new RabbitTemplate(factory);
        template.setMessageConverter(converter);
        return template;
    }
}
