package george.deliveryapi.infrastructure.api.security;

import george.deliveryapi.infrastructure.repositories.auth.JWTUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AuthenticationManager implements ReactiveAuthenticationManager {

    private final JWTUtil jwtUtil;

    @Override
    @SuppressWarnings("unchecked")
    public Mono<Authentication> authenticate(Authentication authentication) {
        String authToken = authentication.getCredentials().toString();
        String username = jwtUtil.getUsernameFromToken(authToken);

        return Mono.just(jwtUtil.validateToken(authToken))
                .filter(valid -> valid)
                .switchIfEmpty(Mono.empty())
                .map(valid -> getAuth(authToken, username));
    }

    private UsernamePasswordAuthenticationToken getAuth(String authToken, String username) {
        Claims claims = jwtUtil.getAllClaimsFromToken(authToken);
        List<String> roleMaps = claims.get("role", List.class);
        return new UsernamePasswordAuthenticationToken(
                username,
                null,
                roleMaps.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())
        );
    }
}
