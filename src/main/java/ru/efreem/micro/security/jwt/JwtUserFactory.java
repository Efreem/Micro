package ru.efreem.micro.security.jwt;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.efreem.micro.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class JwtUserFactory {
    public static JwtUser create(User user) {
        return new JwtUser(
                user.getName(),
                "1234",
                mapToGrantedAuthorities()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities() {
        return List.of(new SimpleGrantedAuthority("READ"), new SimpleGrantedAuthority("WRITE"));
    }
}
