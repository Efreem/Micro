package ru.efreem.micro.service.user;

import ru.efreem.micro.model.User;

import java.util.List;
import java.util.Optional;

public interface DefaultUserService {
    Optional<User> findById(Long id);
    List<User> findByName(String name);
    Optional<User> findByEmail(String email);
    List<User> findByAge(Byte age);
    List<User> findByAgeLessThan(Byte age);
    List<User> findByAgeGreaterThan(Byte age);
    List<User> findByPhoneValue(String phoneValue);
    boolean isCorrectEmail(String email);
    boolean isEmailExists(String email);
}
