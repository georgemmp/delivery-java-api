package george.deliveryapi.infrastructure.api.controllers;

import george.deliveryapi.application.services.DeliveryService;
import george.deliveryapi.domain.entities.Delivery;
import george.deliveryapi.infrastructure.api.dto.DeliveryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/delivery")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;

    @PostMapping
    @PreAuthorize("hasRole('CLIENT')")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Delivery> saveDelivery(Authentication authentication, @RequestBody DeliveryDTO deliveryDTO) {
        String userId = authentication.getName();

        Delivery delivery = Delivery.builder()
                .name(deliveryDTO.getName())
                .build();

        return this.deliveryService.saveDelivery(delivery, userId);
    }
}
