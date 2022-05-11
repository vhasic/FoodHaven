package ba.unsa.etf.nwt.api_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;


//@SpringBootApplication(exclude = {ErrorMvcAutoConfiguration.class}) //ovo nije radilo
//@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@SpringBootApplication
@EnableEurekaClient
public class ApiGatewayApplication{

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    @Bean
    public JwtConfig jwtConfig() {
        return new JwtConfig();
    }
}


