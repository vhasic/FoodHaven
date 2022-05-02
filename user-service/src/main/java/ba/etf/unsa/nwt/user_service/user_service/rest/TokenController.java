package ba.etf.unsa.nwt.user_service.user_service.rest;

import ba.etf.unsa.nwt.user_service.user_service.model.GrantTokenDTO;
import ba.etf.unsa.nwt.user_service.user_service.model.GrantTokenRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;

@RequestMapping(value = "/oauth/token")
public class TokenController extends TokenEndpoint {

    @PostMapping
    public ResponseEntity<GrantTokenDTO> postAccessToken(
            Principal principal,
            @Valid @RequestBody GrantTokenRequest request
    ) throws HttpRequestMethodNotSupportedException {
        OAuth2AccessToken oauthResponse = super.postAccessToken(principal, request.toForm()).getBody();
        return ResponseEntity.ok(new GrantTokenDTO(oauthResponse));
    }
}