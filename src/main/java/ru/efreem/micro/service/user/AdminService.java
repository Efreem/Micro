package ru.efreem.micro.service.user;

public interface AdminService {
    void updateNameById(String name, Long id);
    void updateEmailById(String email, Long id);
}
