package ba.unsa.etf.nwt.rating_service;

import ba.unsa.etf.nwt.rating_service.domain.Review;
import ba.unsa.etf.nwt.rating_service.repos.ReviewRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class RatingServiceApplication {

    private static final Logger log = LoggerFactory.getLogger(RatingServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(RatingServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(ReviewRepository repository) {
        return (args) -> {
            // save a few reviews
            repository.save(new Review(5,"Ovo je testni komentar",1,1));
            repository.save(new Review(4,"Ovo je testni komentar 2",1,2));
            repository.save(new Review(3,"Ovo je testni komentar 3",1,3));

/*            // fetch all reviews
            log.info("Reviews found with findAll():");
            log.info("-------------------------------");
            for (Review review : repository.findAll()) {
                log.info(review.toString());
            }
            log.info("");*/

//            select * from reviewServiceDB.review;

        };
    }
}
