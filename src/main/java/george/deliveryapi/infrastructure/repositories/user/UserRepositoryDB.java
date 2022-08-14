package george.deliveryapi.infrastructure.repositories.user;

import george.deliveryapi.domain.entities.User;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface UserRepositoryDB extends ReactiveMongoRepository<User, String> {

    @Query("{ 'username': ?0 }")
    Mono<User> findByUsername(String username);
}
