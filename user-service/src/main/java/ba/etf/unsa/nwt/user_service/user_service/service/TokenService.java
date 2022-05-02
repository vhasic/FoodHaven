package ba.etf.unsa.nwt.user_service.user_service.service;

import ba.etf.unsa.nwt.user_service.user_service.domain.Token;
import ba.etf.unsa.nwt.user_service.user_service.domain.User;
import ba.etf.unsa.nwt.user_service.user_service.repos.TokenRepository;
import ba.etf.unsa.nwt.user_service.user_service.repos.UserRepository;
import ba.etf.unsa.nwt.user_service.user_service.utility.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class TokenService {
//    private final TokenUtils tokenUtils;
    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;


    public Token generateToken(User user) {
        Token token = createToken(user);
        token = tokenRepository.save(token);
        return token;
    }

    private Token useToken(String tokenPayload) {
        Token token = tokenRepository.findTokenByToken(tokenPayload)
                .orElseThrow(() -> new IllegalArgumentException("Invalid token"));
        if (token.isExpired()) throw new IllegalArgumentException("Invalid token");
        token.setValid(false);
        return tokenRepository.save(token);
    }

    private Token createToken(User user) {
        Integer duration = 24*60*60;//tokenUtils.getActivationTokenDuration();
        Token token = new Token();
        token.setDuration(duration);
        token.setUser(user);
        token.setToken(generateHashString());
//        token.setToken(tokenUtils.generateHashString());
        return token;
    }

    private String generateHashString(){
        Integer tokenLength=64;
        return RandomStringUtils.random(tokenLength, true, true);
    }
}
