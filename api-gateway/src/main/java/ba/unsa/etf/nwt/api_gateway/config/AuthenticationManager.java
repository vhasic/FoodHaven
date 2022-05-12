package ba.unsa.etf.nwt.api_gateway.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
@Slf4j
public class AuthenticationManager implements ReactiveAuthenticationManager {

    @Autowired
    private JwtConfig jwtConfig;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String token = authentication.getCredentials().toString();
        final String SECRET = Base64.getEncoder().encodeToString(jwtConfig.getSecret().getBytes());
        // 4. Validate the token
/*        Claims claims =null;
        try{
        claims = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        return Mono.just(jwtConfig.validateToken(token,SECRET))
                .filter(valid -> valid)
                .switchIfEmpty(Mono.empty())
                .map(valid -> { // ovdje je token validan i može se normalno raditi
                    Claims claims = Jwts.parser()
                            .setSigningKey(SECRET)
                            .parseClaimsJws(token)
                            .getBody();
                    String username = claims.getSubject();
                    List<String> authorities = (List<String>) claims.get("authorities");

                    // 5. Create auth object
                    // UsernamePasswordAuthenticationToken: A built-in object, used by spring to represent the current authenticated / being authenticated user.
                    // It needs a list of authorities, which has type of GrantedAuthority interface, where SimpleGrantedAuthority is an implementation of that interface
                    return new UsernamePasswordAuthenticationToken(
                            username, null, authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
                });
//        return Mono.just(authentication);
    }
}
