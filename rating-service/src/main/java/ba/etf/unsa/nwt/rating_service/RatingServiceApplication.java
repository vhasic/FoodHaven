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

//    @Bean
//    public CommandLineRunner demo(RatingService ratingService) {
//        return (args) -> {
//            // save a few reviews
//            UUID uuid1= UUID.fromString("1124dc7e-1a3a-4a2b-9c8b-c7a3d3ed9476");
//            UUID uuid2= UUID.fromString("d655a515-aa56-40ca-a1cc-78a896e03c5e");
//            UUID uuid3= UUID.fromString("1ac52574-e01a-4675-b379-b276f9a0a0cf");
//            UUID uuid4= UUID.fromString("26873b88-94da-4899-b5f6-a1e43ed5bebd");
//            UUID id1= ratingService.create(new RatingDTO(5,"Ovo je testni komentar",uuid3,uuid2));
//            UUID id2= ratingService.create(new RatingDTO(4,"Ovo je testni komentar 2",uuid4,uuid1));
//            UUID id3= ratingService.create(new RatingDTO(3,"Ovo je testni komentar 3 koji" +
//                    "treba biti mnogooogo mnogo mnogo duzi kako bih provjerila da li sve radi kako treba i da li ce preci u novi" +
//                    "red ako vidi da je ovo kriza dugo",uuid3,uuid1));
//        };
//    }
}
