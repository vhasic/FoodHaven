package ba.unsa.etf.nwt.api_gateway.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Filter that validates the tokens.
 */

@Component
public class JwtTokenAuthenticationFilter implements WebFilter {

    private final JwtConfig jwtConfig;

    public JwtTokenAuthenticationFilter(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        System.out.println("Entered filter");
        ServerHttpRequest request=  exchange.getRequest();
        ServerHttpResponse response=exchange.getResponse();
        // 1. get the authentication header. Tokens are supposed to be passed in the authentication header
        List<String> headers =request.getHeaders().get(jwtConfig.getHeader());
        String header=null;
        if (headers!=null){
            header =request.getHeaders().get(jwtConfig.getHeader()).get(0);
        }

        // 2. validate the header and check the prefix
        if(headers == null || !header.startsWith(jwtConfig.getPrefix())) { // if there is no authorization header or it is not Bearer token
            return chain.filter(exchange);  		// If not valid, go to the next filter.
        }

        // If there is no token provided and hence the user won't be authenticated.
        // It's Ok. Maybe the user accessing a public path or asking for a token.

        // All secured paths that needs a token are already defined and secured in config class.
        // And If user tried to access without access token, then he won't be authenticated and an exception will be thrown.

        // 3. Get the token
        String token = header.replace(jwtConfig.getPrefix(), "");

        try {	// exceptions might be thrown in creating the claims if for example the token is expired
            final String SECRET = Base64.getEncoder().encodeToString(jwtConfig.getSecret().getBytes());
            // 4. Validate the token
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();

            String username = claims.getSubject();
            if(username != null) {
                @SuppressWarnings("unchecked")
                List<String> authorities = (List<String>) claims.get("authorities");

                // 5. Create auth object
                // UsernamePasswordAuthenticationToken: A built-in object, used by spring to represent the current authenticated / being authenticated user.
                // It needs a list of authorities, which has type of GrantedAuthority interface, where SimpleGrantedAuthority is an implementation of that interface
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        username, null, authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));

                // 6. Authenticate the user
                // Now, user is authenticated
                SecurityContextHolder.getContext().setAuthentication(auth);
                System.out.println("User should be authenticated");
            }

        } catch (Exception e) {
            e.printStackTrace();
            // In case of failure. Make sure it's clear; so guarantee user won't be authenticated
            SecurityContextHolder.clearContext();
        }

        // go to the next filter in the filter chain
        return chain.filter(exchange);
    }
}
