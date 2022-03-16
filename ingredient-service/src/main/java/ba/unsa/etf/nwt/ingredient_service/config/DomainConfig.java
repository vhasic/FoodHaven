package ba.unsa.etf.nwt.ingredient_service.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("ba.unsa.etf.nwt.ingredient_service.domain")
@EnableJpaRepositories("ba.unsa.etf.nwt.ingredient_service.repos")
@EnableTransactionManagement
public class DomainConfig {
}
