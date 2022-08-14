package george.deliveryapi.infrastructure.api.dto;

import george.deliveryapi.domain.entities.User.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class UserDeliverymanDTO {
    private String username;
    private String password;
    private String cpf;
    private final List<Role> role;

    public UserDeliverymanDTO() {
        this.role = new ArrayList<>();
        role.add(Role.ROLE_DELIVERYMAN);
    }
}
