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
//            UUID uuid1= UUID.fromString("989961b9-11f0-4455-ae14-6c5a29b7e346");
//            UUID uuid2= UUID.fromString("d35052ff-00ce-4bd2-8bf3-aaa9f87cac54");
//            UUID uuid3= UUID.fromString("04b03880-5b56-4464-a466-a150958c32f7");
//            UUID uuid4= UUID.fromString("04b03880-5b56-4464-a466-a150958c32f7");
//            UUID id1= ratingService.create(new RatingDTO(5,"Ovo je testni komentar",uuid3,uuid2));
//            UUID id2= ratingService.create(new RatingDTO(4,"Ovo je testni komentar 2",uuid4,uuid1));
//            UUID id3= ratingService.create(new RatingDTO(3,"Ovo je testni komentar 3 koji" +
//                    "treba biti mnogooogo mnogo mnogo duzi kako bih provjerila da li sve radi kako treba i da li ce preci u novi" +
//                    "red ako vidi da je ovo kriza dugo",uuid3,uuid1));
//        };
//    }
}
