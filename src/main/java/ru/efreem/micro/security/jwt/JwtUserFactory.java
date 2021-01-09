package ru.efreem.micro.security.jwt;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.efreem.micro.auth.AuthUser;
import ru.efreem.micro.auth.Role;

import java.util.List;
import java.util.stream.Collectors;

public class JwtUserFactory {

    public JwtUserFactory() {

    }

    public static JwtUser create(AuthUser user) {
        return new JwtUser(user.getId(),
                           user.getUsername(),
                           user.getPassword(),
                           toGrantedAuthorities(user.getRoles()));
    }

    private static List<GrantedAuthority> toGrantedAuthorities(List<Role> userRoles) {
        return userRoles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

}
