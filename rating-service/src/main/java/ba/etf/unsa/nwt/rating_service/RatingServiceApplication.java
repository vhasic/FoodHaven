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
            UUID uuid1= UUID.fromString("51f14681-7726-4cc9-9ad1-966970b0e08c");
            UUID uuid2= UUID.fromString("c2f71975-3f5a-46d1-af30-ec17d14c9f66");
            UUID id1= ratingService.create(new RatingDTO(5,"Ovo je testni komentar",uuid1,uuid2));
            UUID id2= ratingService.create(new RatingDTO(4,"Ovo je testni komentar 2",uuid2,uuid1));
            UUID id3= ratingService.create(new RatingDTO(3,"Ovo je testni komentar 3 koji" +
                    "treba biti mnogooogo mnogo mnogo duzi kako bih provjerila da li sve radi kako treba i da li ce preci u novi" +
                    "red ako vidi da je ovo kriza dugo",uuid1,uuid1));
        };
    }
}
