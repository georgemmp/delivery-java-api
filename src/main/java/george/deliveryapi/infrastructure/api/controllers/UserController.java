package george.deliveryapi.infrastructure.api.controllers;

import george.deliveryapi.application.services.UserService;
import george.deliveryapi.domain.entities.User;
import george.deliveryapi.infrastructure.api.dto.UserClientDTO;
import george.deliveryapi.infrastructure.api.dto.UserDeliverymanDTO;
import george.deliveryapi.infrastructure.repositories.auth.PasswordEncoderImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final PasswordEncoderImpl passwordEncoderImpl;

    @PostMapping(value = "/client")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<User> saveClient(@RequestBody UserClientDTO userClientDTO) {
        User user = new User();
        BeanUtils.copyProperties(userClientDTO, user);
        user.setPassword(passwordEncoderImpl.encode(user.getPassword()));
        return this.userService.saveUser(user);
    }

    @PostMapping(value = "/deliveryman")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<User> saveDeliveryman(@RequestBody UserDeliverymanDTO userDeliverymanDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDeliverymanDTO, user);
        user.setPassword(passwordEncoderImpl.encode(user.getPassword()));
        return this.userService.saveUser(user);
    }

}
