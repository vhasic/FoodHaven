package ba.etf.unsa.nwt.user_service.rest;

import ba.etf.unsa.nwt.user_service.model.TokenDTO;
import ba.etf.unsa.nwt.user_service.service.TokenService;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/tokens", produces = MediaType.APPLICATION_JSON_VALUE)
public class TokenController {

    private final TokenService tokenService;

    public TokenController(final TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @GetMapping
    public ResponseEntity<List<TokenDTO>> getAllTokens() {
        return ResponseEntity.ok(tokenService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TokenDTO> getToken(@PathVariable final UUID id) {
        return ResponseEntity.ok(tokenService.get(id));
    }

    @PostMapping
    public ResponseEntity<UUID> createToken(@RequestBody @Valid final TokenDTO tokenDTO) {
        return new ResponseEntity<>(tokenService.create(tokenDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateToken(@PathVariable final UUID id,
            @RequestBody @Valid final TokenDTO tokenDTO) {
        tokenService.update(id, tokenDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteToken(@PathVariable final UUID id) {
        tokenService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
