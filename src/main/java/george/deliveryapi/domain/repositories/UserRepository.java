package george.deliveryapi.domain.repositories;

import george.deliveryapi.domain.entities.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepository {

    Mono<User> save(User user);
    Mono<User> findById(String userId);
    Mono<Void> delete(User user);
    Mono<User> update(User user);
    Mono<User> findByUsername(String username);

}
