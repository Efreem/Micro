package ru.efreem.micro.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.efreem.micro.auth.AuthUser;
import ru.efreem.micro.auth.Role;
import ru.efreem.micro.security.jwt.JwtUser;
import ru.efreem.micro.security.jwt.JwtUserFactory;

import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUser user = new AuthUser();

        user.setId(1L);
        user.setUsername(username);
        user.setPassword("1234");
        user.setRoles(List.of(new Role("ROLE_ADMIN")));

        JwtUser jwtUser = JwtUserFactory.create(user);
        System.out.println("Loaded user with username " + username);

        return jwtUser;
    }

}
