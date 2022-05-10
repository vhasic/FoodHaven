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
//            UUID uuid1= UUID.fromString("82ebb0ee-4d62-491d-8afa-a8253b39b38f");
//            UUID uuid2= UUID.fromString("8355d636-3d0c-41e0-a759-95f115b46d52");
//            UUID id1= ratingService.create(new RatingDTO(5,"Ovo je testni komentar",uuid1,uuid2));
//            UUID id2= ratingService.create(new RatingDTO(4,"Ovo je testni komentar 2",uuid2,uuid1));
//            UUID id3= ratingService.create(new RatingDTO(3,"Ovo je testni komentar 3",uuid1,uuid1));
        };
    }
}
