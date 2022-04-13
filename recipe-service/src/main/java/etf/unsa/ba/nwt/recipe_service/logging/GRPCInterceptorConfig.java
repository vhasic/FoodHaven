package etf.unsa.ba.nwt.recipe_service.logging;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class GRPCInterceptorConfig extends WebMvcConfigurerAdapter {
    @Autowired
    GRPCInterceptor grpcInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(grpcInterceptor);
    }
}
