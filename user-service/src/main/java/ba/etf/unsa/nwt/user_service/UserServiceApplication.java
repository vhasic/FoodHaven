package ba.etf.unsa.nwt.user_service;

import ba.etf.unsa.nwt.user_service.model.RoleDTO;
import ba.etf.unsa.nwt.user_service.model.UserDTO;
import ba.etf.unsa.nwt.user_service.service.RoleService;
import ba.etf.unsa.nwt.user_service.service.TokenService;
import ba.etf.unsa.nwt.user_service.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;


@SpringBootApplication
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(RoleService roleService, TokenService tokenService, UserService userService) {
        return (args) -> {
            UUID adminId=roleService.create(new RoleDTO("Administrator"));
            UUID userId=roleService.create(new RoleDTO("User"));
            // save a few users
            userService.create(new UserDTO("Administrator","Administrator","admin","admin@nesto.com","password",adminId));
            userService.create(new UserDTO("User","User","user","user@nesto.com","password",userId));
        };
    }
}
