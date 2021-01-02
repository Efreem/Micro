package ru.efreem.micro.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.efreem.micro.model.User;
import ru.efreem.micro.repos.UserRepository;
import java.util.List;
import java.util.Optional;

@Service
public class AdminUserService implements AdminService, DefaultService {
    private UserRepository userRepository;

    private static final String LOG_PATTERN = "EXECUTED METHOD: ";

    @Autowired
    public AdminUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findByName(String name) {
        System.out.println(LOG_PATTERN + "findByName");

        return userRepository.findByName(name);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        System.out.println(LOG_PATTERN + "findByEmail");

        return userRepository.findByEmail(email);
    }

    @Override
    public void updateNameById(String name, Long id) {
        userRepository.updateNameById(name, id);

        System.out.println(LOG_PATTERN + "updateNameById");
    }

    @Override
    public void updateEmailById(String email, Long id) {

        if (isCorrectEmail(email)) {
            userRepository.updateEmailById(email, id);
        }

        System.out.println(LOG_PATTERN + "updateEmailById");
    }

    public boolean isCorrectEmail(String email) {
        return email.matches("[a-zA-Z0-9]*@[a-zA-Z0-9]*\\.[a-z]*");
    }

}
