package ba.etf.unsa.nwt.user_service.user_service;

import ba.etf.unsa.nwt.user_service.user_service.model.RoleDTO;
import ba.etf.unsa.nwt.user_service.user_service.model.UserDTO;
import ba.etf.unsa.nwt.user_service.user_service.service.RoleService;
import ba.etf.unsa.nwt.user_service.user_service.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;


//@SpringBootApplication(scanBasePackages={"ba.etf.unsa.nwt.user_service.user_service"})
@SpringBootApplication
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner demo(RoleService roleService, UserService userService) {
//        return (args) -> {
//            UUID adminId=roleService.create(new RoleDTO("Administrator"));
//            UUID userId=roleService.create(new RoleDTO("User"));
//            // save a few users
//            userService.create(new UserDTO("Administrator","Administrator","admin","admin@nesto.com","Password1!",adminId));
//            userService.create(new UserDTO("User","User","user","user@nesto.com","Password1!",userId));
//        };
//    }

//    Documentation on: http://localhost:8080/swagger-ui.html
//    OpenAPI description will be available at the following url for json format: http://localhost:8080/v3/api-docs
}
