package ba.unsa.etf.nwt.api_gateway;

import ba.unsa.etf.nwt.api_gateway.security.JwtConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;


//@SpringBootApplication(exclude = {ErrorMvcAutoConfiguration.class}) //ovo nije radilo
//@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@SpringBootApplication
@EnableEurekaClient
@EnableWebFluxSecurity
public class ApiGatewayApplication{

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    @Bean
    public JwtConfig jwtConfig() {
        return new JwtConfig();
    }
}


