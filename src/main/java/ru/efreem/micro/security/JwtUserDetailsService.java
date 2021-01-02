package ru.efreem.micro.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.efreem.micro.model.User;
import ru.efreem.micro.repos.UserRepository;
import ru.efreem.micro.security.jwt.JwtUser;
import ru.efreem.micro.security.jwt.JwtUserFactory;

public class JwtUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    @Autowired
    public JwtUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = new User();

        user.setId(1L);
        user.setEmail("ex@mail.ru");

        JwtUser jwtUser = JwtUserFactory.create(user);
        return jwtUser;
    }

}
