package ru.efreem.micro.service.user;

import ru.efreem.micro.model.User;

import java.util.List;
import java.util.Optional;

public interface DefaultService {
    Optional<User> findById(Long id);
    List<User> findByName(String name);
    Optional<User> findByEmail(String email);
}
