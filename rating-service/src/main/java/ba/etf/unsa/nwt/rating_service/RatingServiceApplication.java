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
//            UUID uuid=UUID.randomUUID();
            UUID uuid1= UUID.fromString("ce67e275-9f05-44d4-b401-cd0ac67f588a");
            UUID uuid2= UUID.fromString("f7babcf7-3e1e-4482-af7d-1ff7c7921e20");
            UUID id1= ratingService.create(new RatingDTO(5,"Ovo je testni komentar",uuid1,uuid2));
            UUID id2= ratingService.create(new RatingDTO(4,"Ovo je testni komentar 2",uuid2,uuid1));
            UUID id3= ratingService.create(new RatingDTO(3,"Ovo je testni komentar 3",uuid1,uuid1));
        };
    }
}
