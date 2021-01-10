package ru.efreem.micro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.efreem.micro.auth.AuthUser;
import ru.efreem.micro.auth.Role;
import ru.efreem.micro.security.jwt.JwtTokenProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @GetMapping("login/{password}")
    public ResponseEntity login(@PathVariable(name = "password") String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("1234", password));
            AuthUser authUser = new AuthUser();

            authUser.setId(1L);
            authUser.setUsername("1234");
            authUser.setPassword(password);
            authUser.setRoles(List.of(new Role("ADMIN")));

            if (authUser == null) {
                throw new UsernameNotFoundException("User with username: " + authUser.getUsername() + " not found");
            }

            String token = jwtTokenProvider.createToken("1234", authUser.getRoles());

            Map<Object, Object> response = new HashMap<>();
            response.put("username", "1234");
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
