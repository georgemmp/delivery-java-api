package george.deliveryapi.infrastructure.repositories.delivery;

import george.deliveryapi.domain.entities.Delivery;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface DeliveryRepositoryDB extends ReactiveMongoRepository<Delivery, String> {
}
