package george.deliveryapi.application.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class BusinessError extends RuntimeException {

    private String message;
    private HttpStatus status;
}
