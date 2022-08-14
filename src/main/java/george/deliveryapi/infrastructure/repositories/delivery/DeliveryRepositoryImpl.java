package george.deliveryapi.infrastructure.repositories.delivery;

import george.deliveryapi.domain.entities.Delivery;
import george.deliveryapi.domain.repositories.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class DeliveryRepositoryImpl implements DeliveryRepository {

    private final DeliveryRepositoryDB deliveryRepositoryDB;

    @Override
    public Mono<Delivery> save(Delivery delivery, String userId) {
        return deliveryRepositoryDB.save(delivery);
    }
}
