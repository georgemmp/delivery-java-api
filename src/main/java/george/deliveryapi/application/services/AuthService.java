package george.deliveryapi.application.services;

import george.deliveryapi.application.exceptions.AuthenticationException;
import george.deliveryapi.domain.entities.Auth;
import george.deliveryapi.domain.entities.Token;
import george.deliveryapi.domain.repositories.JWTUtilRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final JWTUtilRepository jwtUtilRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    public Mono<Token> login(Auth auth) {
        return this.userService.findByUsername(auth.getUsername())
                .filter(user -> passwordEncoder.matches(auth.getPassword(), user.getPassword()))
                .map(user -> new Token(jwtUtilRepository.generateToken(user)))
                .switchIfEmpty(Mono.error(new AuthenticationException("Username or password not match")))
                .doOnError(error -> log.error("ERROR: {}", error.toString()));
    }
}
