package george.deliveryapi.application.services;

import george.deliveryapi.domain.entities.User;
import george.deliveryapi.domain.repositories.UserRepository;
import george.deliveryapi.application.exceptions.BusinessError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public Mono<User> saveUser(User user) {
        return this.userRepository.save(user)
                .doOnSuccess(u -> log.info("Usuário salvo"))
                .doOnError(error -> log.error("ERROR: {}", error.toString()));
    }

    public Mono<User> findByUsername(String username) {
        return this.userRepository.findByUsername(username)
                .switchIfEmpty(Mono.error(new BusinessError("Username or password not match",
                        HttpStatus.NOT_FOUND)))
                .doOnSuccess(u -> log.info("User {} found", username))
                .doOnError(error -> log.error("ERROR: {}", error.toString()));
    }

}
