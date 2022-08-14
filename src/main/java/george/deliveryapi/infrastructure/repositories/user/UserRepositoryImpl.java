package george.deliveryapi.infrastructure.repositories.user;

import george.deliveryapi.domain.entities.User;
import george.deliveryapi.domain.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserRepositoryDB userRepositoryDB;

    @Override
    public Mono<User> save(User user) {
        return this.userRepositoryDB.save(user);
    }

    @Override
    public Mono<User> findById(String userId) {
        return this.userRepositoryDB.findById(userId);
    }

    @Override
    public Mono<Void> delete(User user) {
        return this.userRepositoryDB.delete(user);
    }

    @Override
    public Mono<User> update(User user) {
        return this.userRepositoryDB.save(user);
    }

    @Override
    public Mono<User> findByUsername(String username) {
        return this.userRepositoryDB.findByUsername(username);
    }

}
