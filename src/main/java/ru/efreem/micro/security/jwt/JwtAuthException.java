package ru.efreem.micro.security.jwt;

import javax.naming.AuthenticationException;

public class JwtAuthException extends AuthenticationException {
    public JwtAuthException(String message) {
        super(message);
    }
}
