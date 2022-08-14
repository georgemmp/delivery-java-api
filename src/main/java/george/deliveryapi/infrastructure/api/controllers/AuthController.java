package george.deliveryapi.infrastructure.api.controllers;

import george.deliveryapi.application.services.AuthService;
import george.deliveryapi.domain.entities.Auth;
import george.deliveryapi.domain.entities.Token;
import george.deliveryapi.infrastructure.api.dto.AuthenticationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public Mono<Token> login(@RequestBody AuthenticationDTO authenticationDTO) {
        Auth auth = new Auth();
        BeanUtils.copyProperties(authenticationDTO, auth);
        return authService.login(auth);
    }

}

