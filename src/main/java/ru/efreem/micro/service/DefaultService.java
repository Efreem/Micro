package ru.efreem.micro.service;

import ru.efreem.micro.model.User;

import java.util.List;

public interface DefaultService {
    List<User> getUserByGreaterAge(Byte age);
    List<User> getUserByUnderAge(Byte age);
    List<User> getUserByAge(Byte age);
    List<User> getUserByPhone(String phone);
    List<User> getUserByName(String name);
    List<User> getUserByEmail(String email);
}
