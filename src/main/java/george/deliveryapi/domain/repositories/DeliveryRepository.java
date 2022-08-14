package george.deliveryapi.domain.repositories;

import george.deliveryapi.domain.entities.Delivery;
import reactor.core.publisher.Mono;

public interface DeliveryRepository {

    Mono<Delivery> save(Delivery delivery, String userId);

}
