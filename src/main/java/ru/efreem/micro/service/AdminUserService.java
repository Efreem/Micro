package ru.efreem.micro.service;

import org.graalvm.compiler.lir.LIRInstruction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.efreem.micro.model.Profile;
import ru.efreem.micro.model.User;
import ru.efreem.micro.repos.ProfileRepository;
import ru.efreem.micro.repos.UserRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AdminUserService implements AdminService, DefaultService {
    private UserRepository userRepository;
    private ProfileRepository profileRepository;

    @Autowired
    public AdminUserService(UserRepository userRepository, ProfileRepository profileRepository) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
    }

    public void updateProfileCashById(BigDecimal cash, Long id) {
        return profileRepository.updateCashById(cash, id);
    }

    public List<User> findByGreaterAge(Byte age) {
        return userRepository.findByAgeGreaterThan(age);
    }

    public List<User> findByLessAge(Byte age) {
        return userRepository.findByAgeLessThan(age);
    }

    public List<User> findByAge(Byte age) {
        return userRepository.findByAge(age);
    }

    public List<User> getUserByPhone(String phone) {
        return null;
    }

    public List<User> getUserByName(String name) {
        return null;
    }

    public List<User> getUserByEmail(String email) {
        return null;
    }

}
