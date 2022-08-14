package george.deliveryapi.domain.repositories;

import george.deliveryapi.domain.entities.User;
import io.jsonwebtoken.Claims;

import java.util.Date;

public interface JWTUtilRepository {

    Claims getAllClaimsFromToken(String token);
    String getUsernameFromToken(String token);
    Date getExpirationDateFromToken(String token);
    String generateToken(User user);
    Boolean validateToken(String token);
}
