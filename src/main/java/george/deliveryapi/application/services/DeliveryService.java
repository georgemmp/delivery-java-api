package george.deliveryapi.application.services;

import george.deliveryapi.application.exceptions.BusinessError;
import george.deliveryapi.domain.entities.Delivery;
import george.deliveryapi.domain.entities.User;
import george.deliveryapi.domain.repositories.DeliveryRepository;
import george.deliveryapi.domain.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final UserRepository userRepository;

    public Mono<Delivery> saveDelivery(Delivery delivery, String userId) {
        delivery.setClientId(userId);
        delivery.setOnTheWay(false);

        return this.userRepository.findById(userId)
                .map(this::verifyUser)
                .switchIfEmpty(Mono.error(new BusinessError(
                                "O usuário que solicitou o pedido não tem o perfil de cliente",
                                HttpStatus.BAD_REQUEST)))
                .then(this.deliveryRepository.save(delivery, userId))
                .doOnSuccess(d -> log.info("Pedido feito {}", d))
                .doOnError(error -> log.error("ERROR: {}", error.toString()));
    }

    private boolean verifyUser(User user) {
        return user.getRole()
                    .stream()
                    .anyMatch(description -> description == User.Role.ROLE_CLIENT);
    }
}
