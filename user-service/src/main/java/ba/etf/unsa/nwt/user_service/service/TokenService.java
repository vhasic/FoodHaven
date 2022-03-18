package ba.etf.unsa.nwt.user_service.service;

import ba.etf.unsa.nwt.user_service.domain.Token;
import ba.etf.unsa.nwt.user_service.domain.User;
import ba.etf.unsa.nwt.user_service.model.TokenDTO;
import ba.etf.unsa.nwt.user_service.repos.TokenRepository;
import ba.etf.unsa.nwt.user_service.repos.UserRepository;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class TokenService {

    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;

    public TokenService(final TokenRepository tokenRepository,
            final UserRepository userRepository) {
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
    }

    public List<TokenDTO> findAll() {
        return tokenRepository.findAll()
                .stream()
                .map(token -> mapToDTO(token, new TokenDTO()))
                .collect(Collectors.toList());
    }

    public TokenDTO get(final UUID id) {
        return tokenRepository.findById(id)
                .map(token -> mapToDTO(token, new TokenDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public UUID create(final TokenDTO tokenDTO) {
        final Token token = new Token();
        mapToEntity(tokenDTO, token);
        return tokenRepository.save(token).getId();
    }

    public void update(final UUID id, final TokenDTO tokenDTO) {
        final Token token = tokenRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(tokenDTO, token);
        tokenRepository.save(token);
    }

    public void delete(final UUID id) {
        tokenRepository.deleteById(id);
    }

    private TokenDTO mapToDTO(final Token token, final TokenDTO tokenDTO) {
        tokenDTO.setId(token.getId());
        tokenDTO.setToken(token.getToken());
        tokenDTO.setType(token.getType());
        tokenDTO.setDuration(token.getDuration());
        tokenDTO.setValid(token.getValid());
        tokenDTO.setUser(token.getUser() == null ? null : token.getUser().getId());
        return tokenDTO;
    }

    private Token mapToEntity(final TokenDTO tokenDTO, final Token token) {
        token.setToken(tokenDTO.getToken());
        token.setType(tokenDTO.getType());
        token.setDuration(tokenDTO.getDuration());
        token.setValid(tokenDTO.getValid());
        if (tokenDTO.getUser() != null && (token.getUser() == null || !token.getUser().getId().equals(tokenDTO.getUser()))) {
            final User user = userRepository.findById(tokenDTO.getUser())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));
            token.setUser(user);
        }
        return token;
    }

}
