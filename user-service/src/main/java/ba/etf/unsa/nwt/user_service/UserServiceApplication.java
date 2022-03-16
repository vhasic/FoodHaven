package ba.etf.unsa.nwt.user_service;

import ba.etf.unsa.nwt.user_service.domain.Role;
import ba.etf.unsa.nwt.user_service.domain.User;
import ba.etf.unsa.nwt.user_service.repos.RoleRepository;
import ba.etf.unsa.nwt.user_service.repos.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class UserServiceApplication {

    private static final Logger log = LoggerFactory.getLogger(UserServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }


    @Bean
    public CommandLineRunner demo(RoleRepository roleRepository, UserRepository userRepository) {
        return (args) -> {
            roleRepository.save(new Role("Administrator"));
            roleRepository.save(new Role("User"));
            // save a few users
            Role roleAdmin=roleRepository.findById(1).get();
            Role roleUser=roleRepository.findById(2).get();
            userRepository.save(new User("Administrator","Administrator","admin","admin@nesto.com","password",roleAdmin));
            userRepository.save(new User("User","User","user","user@nesto.com","password",roleUser));

        };
    }
}
