package ba.etf.unsa.nwt.rating_service.config;

import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    @LoadBalanced
    @Bean
    RestTemplate loadBalanced () {
        return new RestTemplate();
    }

    @Primary
    @Bean
    RestTemplate restTemplate () {
        return new RestTemplate();
    }
}
