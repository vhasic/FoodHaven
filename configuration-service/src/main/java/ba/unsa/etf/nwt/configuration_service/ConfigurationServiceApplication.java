package ba.unsa.etf.nwt.configuration_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;


@SpringBootApplication
@EnableConfigServer
public class ConfigurationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigurationServiceApplication.class, args);
    }
//http://root:secret@localhost:8888/config-client
//http://root:secret@localhost:8888
//http://root:secret@localhost:8888/rating-service/local
}
