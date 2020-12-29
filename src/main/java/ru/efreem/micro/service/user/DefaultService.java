package ru.efreem.micro.service.user;

import ru.efreem.micro.model.User;

import java.util.List;
import java.util.Optional;

public interface DefaultService {
    Optional<User> findById(Long id);
    List<User> findByGreaterAge(Byte age);
    List<User> findByLessAge(Byte age);
    List<User> findByAge(Byte age);
    User findByPhone(String phone);
    List<User> findByName(String name);
    List<User> findByEmail(String email);
}
