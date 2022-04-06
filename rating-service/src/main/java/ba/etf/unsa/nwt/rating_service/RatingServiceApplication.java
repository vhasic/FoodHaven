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
//            UUID uuid1= UUID.fromString("21a7dc09-8e5c-4920-b02d-b3c63e1bf9c3");
//            UUID uuid2= UUID.fromString("2879d230-5692-4b1b-8443-868c5cca152c");
//            UUID id1= ratingService.create(new RatingDTO(5,"Ovo je testni komentar",uuid1,uuid2));
//            UUID id2= ratingService.create(new RatingDTO(4,"Ovo je testni komentar 2",uuid2,uuid1));
//            UUID id3= ratingService.create(new RatingDTO(3,"Ovo je testni komentar 3",uuid1,uuid1));
        };
    }
}
