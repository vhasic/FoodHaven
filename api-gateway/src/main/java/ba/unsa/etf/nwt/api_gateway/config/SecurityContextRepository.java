package ba.unsa.etf.nwt.api_gateway.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

//Getting the token and forwarding to auth manager
@AllArgsConstructor
@Component
public class SecurityContextRepository implements ServerSecurityContextRepository {

    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtConfig jwtConfig;

    @Override
    public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange serverWebExchange) {
        return Mono.justOrEmpty(serverWebExchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION))
                .filter(authHeader -> authHeader.startsWith("Bearer "))
                .flatMap(authHeader -> {
                    String token = authHeader.substring(7);
                    Authentication authentication = new UsernamePasswordAuthenticationToken(token, token);
                    return this.authenticationManager.authenticate(authentication).map(SecurityContextImpl::new);
/*                    String username = null;
                    List<String> authorities = null;

                    try {
                        String token = authHeader.substring(7);
                        final String SECRET = Base64.getEncoder().encodeToString(jwtConfig.getSecret().getBytes());
                        // 4. Validate the token
                        Claims claims = Jwts.parser()
                                .setSigningKey(SECRET)
                                .parseClaimsJws(token)
                                .getBody();

                        username = claims.getSubject();
                        authorities = (List<String>) claims.get("authorities");

                    } catch ( Exception e ) {
                        e.printStackTrace();
                    }

                    // 5. Create auth object
                    // UsernamePasswordAuthenticationToken: A built-in object, used by spring to represent the current authenticated / being authenticated user.
                    // It needs a list of authorities, which has type of GrantedAuthority interface, where SimpleGrantedAuthority is an implementation of that interface
                    Authentication authentication = new UsernamePasswordAuthenticationToken(
                            username, null, authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
                    return this.authenticationManager.authenticate(authentication).map(SecurityContextImpl::new);*/
                });
    }
}
