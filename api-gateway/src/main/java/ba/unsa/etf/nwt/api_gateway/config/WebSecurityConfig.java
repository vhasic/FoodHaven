package ba.unsa.etf.nwt.api_gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

/**
 * WebSecurityConfig class
 **/


@EnableReactiveMethodSecurity
@EnableWebFluxSecurity
public class WebSecurityConfig {

    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    SecurityContextRepository securityContextRepository;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http/*, AuthenticationManager authManager*/) {
//        return http.csrf().disable().authorizeExchange().anyExchange().permitAll().and().build();
        return http.csrf().disable()
                .httpBasic().disable()
                .formLogin().disable()
                .authenticationManager(authenticationManager)
                .securityContextRepository(securityContextRepository)
                .authorizeExchange()
                    .pathMatchers(HttpMethod.OPTIONS).permitAll()
                    .pathMatchers( HttpMethod.POST, jwtConfig.getUri()).permitAll()
                    .anyExchange().authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint((swe, e) -> {
                    System.out.println("[1] Authentication error: Unauthorized[401]: " + e.getMessage());

                    return Mono.fromRunnable(() -> swe.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED));
                })
                .accessDeniedHandler((swe, e) -> {
                    System.out.println("[2] Authentication error: Access Denied[401]: " + e.getMessage());

                    return Mono.fromRunnable(() -> swe.getResponse().setStatusCode(HttpStatus.FORBIDDEN));
                })
                .and()
//                .addFilterAt(new JwtTokenAuthenticationFilter(jwtConfig),SecurityWebFiltersOrder.AUTHENTICATION)
                .build();
/*        return http
                .authorizeExchange()
                .pathMatchers(HttpMethod.OPTIONS).permitAll()
//                .pathMatchers( HttpMethod.POST, jwtConfig.getUri()).permitAll()
                .pathMatchers( HttpMethod.POST, "/auth/").permitAll()
                .pathMatchers( HttpMethod.GET, "/api/users").permitAll() //test
                .anyExchange()
                .authenticated()
                .and()
                .csrf()
                .disable()
                .httpBasic()
                .disable()
                .formLogin()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint((swe, e) -> {
                    System.out.println("[1] Authentication error: Unauthorized[401]: " + e.getMessage());

                    return Mono.fromRunnable(() -> swe.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED));
                })
                .accessDeniedHandler((swe, e) -> {
                    System.out.println("[2] Authentication error: Access Denied[401]: " + e.getMessage());

                    return Mono.fromRunnable(() -> swe.getResponse().setStatusCode(HttpStatus.FORBIDDEN));
                })
                .and()
                .addFilterAt(new JwtTokenAuthenticationFilter(jwtConfig),SecurityWebFiltersOrder.AUTHENTICATION)
//                .addFilterAfter(new JwtTokenAuthenticationFilter(jwtConfig),SecurityWebFiltersOrder.AUTHENTICATION)
//                .addFilterAt(bearerAuthenticationFilter(authManager), SecurityWebFiltersOrder.AUTHENTICATION)
//                .addFilterAt(cookieAuthenticationFilter(authManager), SecurityWebFiltersOrder.AUTHENTICATION)
                .build();*/

    }

/**
     * Spring security works by filter chaining.
     * We need to add a JWT CUSTOM FILTER to the chain.
     *
     * what is AuthenticationWebFilter:
     *
     *  A WebFilter that performs authentication of a particular request. An outline of the logic:
     *  A request comes in and if it does not match setRequiresAuthenticationMatcher(ServerWebExchangeMatcher),
     *  then this filter does nothing and the WebFilterChain is continued.
     *  If it does match then... An attempt to convert the ServerWebExchange into an Authentication is made.
     *  If the result is empty, then the filter does nothing more and the WebFilterChain is continued.
     *  If it does create an Authentication...
     *  The ReactiveAuthenticationManager specified in AuthenticationWebFilter(ReactiveAuthenticationManager) is used to perform authentication.
     *  If authentication is successful, ServerAuthenticationSuccessHandler is invoked and the authentication is set on ReactiveSecurityContextHolder,
     *  else ServerAuthenticationFailureHandler is invoked
     **/


/*    AuthenticationWebFilter bearerAuthenticationFilter(AuthenticationManager authManager) {
        AuthenticationWebFilter bearerAuthenticationFilter = new AuthenticationWebFilter(authManager);
        bearerAuthenticationFilter.setAuthenticationConverter(new ServerHttpBearerAuthenticationConverter(new JwtVerifyHandler(jwtSecret)));
        bearerAuthenticationFilter.setRequiresAuthenticationMatcher(ServerWebExchangeMatchers.pathMatchers("/**"));

        return bearerAuthenticationFilter;
    }

    AuthenticationWebFilter cookieAuthenticationFilter(AuthenticationManager authManager) {
        AuthenticationWebFilter cookieAuthenticationFilter = new AuthenticationWebFilter(authManager);
        cookieAuthenticationFilter.setAuthenticationConverter(new ServerHttpCookieAuthenticationConverter(new JwtVerifyHandler(jwtSecret)));
        cookieAuthenticationFilter.setRequiresAuthenticationMatcher(ServerWebExchangeMatchers.pathMatchers("/**"));

        return cookieAuthenticationFilter;
    }*/

}
