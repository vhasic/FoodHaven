package etf.unsa.ba.nwt.recipe_service.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("etf.unsa.ba.nwt.recipe_service.domain")
@EnableJpaRepositories("etf.unsa.ba.nwt.recipe_service.repos")
@EnableTransactionManagement
public class DomainConfig {
}
