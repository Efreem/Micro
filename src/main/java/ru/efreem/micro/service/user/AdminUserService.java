package ru.efreem.micro.service.user;

import ru.efreem.micro.model.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface AdminUserService {
    void updateNameById(String name, Long id);
    void updateEmailById(String email, Long id);
    Optional<User> findById(Long id);
    List<User> findByName(String name);
    Optional<User> findByEmail(String email);
    List<User> findByAge(Byte age);
    List<User> findByPhoneValue(String phoneValue);
    List<User> findByAgeGreaterThan(Byte age);
    List<User> findByAgeLessThan(Byte age);
    boolean isCorrectEmail(String email);
    boolean isEmailExists(String email);
    Object saveUser(String name, String email, String phoneValue, BigDecimal cash, Byte age);
}
