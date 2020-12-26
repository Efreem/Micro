package ru.efreem.micro.service;

import ru.efreem.micro.model.User;

import java.util.List;

public interface DefaultService {
    List<User> findByGreaterAge(Byte age);
    List<User> findByLessAge(Byte age);
    List<User> findByAge(Byte age);
    User findByPhone(String phone);
    List<User> findByName(String name);
    List<User> findByEmail(String email);
}
