package ba.etf.unsa.nwt.rating_service;

import ba.etf.unsa.nwt.rating_service.model.RatingDTO;
import ba.etf.unsa.nwt.rating_service.service.RatingService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;


@SpringBootApplication
public class RatingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RatingServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(RatingService ratingService) {
        return (args) -> {
            // save a few reviews
            UUID uuid=UUID.randomUUID();
            UUID id1= ratingService.create(new RatingDTO(5,"Ovo je testni komentar",uuid,uuid));
            UUID id2= ratingService.create(new RatingDTO(4,"Ovo je testni komentar 2",uuid,uuid));
            UUID id3= ratingService.create(new RatingDTO(3,"Ovo je testni komentar 3",uuid,uuid));
        };
    }
}
