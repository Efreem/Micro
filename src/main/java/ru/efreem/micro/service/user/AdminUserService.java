package ru.efreem.micro.service.user;

import ru.efreem.micro.model.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface AdminUserService {
    Object updateNameById(String name, Long id);
    Object updateEmailById(String email, Long id);
    boolean isCorrectEmail(String email);
    boolean isEmailExists(String email);
    Object saveUser(String name, String email, String phoneValue, BigDecimal cash, Byte age);
}
