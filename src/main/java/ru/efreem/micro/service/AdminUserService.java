package ru.efreem.micro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.efreem.micro.model.Phone;
import ru.efreem.micro.model.Profile;
import ru.efreem.micro.model.User;
import ru.efreem.micro.repos.PhoneRepository;
import ru.efreem.micro.repos.ProfileRepository;
import ru.efreem.micro.repos.UserRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminUserService implements AdminService, DefaultService {
    private UserRepository userRepository;
    private ProfileRepository profileRepository;
    private PhoneRepository phoneRepository;

    private static final String LOG_PATTERN = "EXECUTED METHOD: ";
    @Autowired
    public AdminUserService(UserRepository userRepository, ProfileRepository profileRepository,
                            PhoneRepository phoneRepository) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
        this.phoneRepository = phoneRepository;
    }

    public void updateProfileCashById(BigDecimal cash, Long id) {
        profileRepository.updateCashById(cash, id);
        System.out.println(LOG_PATTERN + "updateCashById");
    }

    public List<User> findByGreaterAge(Byte age) {
        List<User> users = new ArrayList<>();
        List<Profile> profiles = profileRepository.findByAgeGreaterThan(age);

        for (Profile profile : profiles) {
            users.add(profile.getUser());
        }

        System.out.println(LOG_PATTERN + "findByGreaterAge");

        return users;
    }

    public List<User> findByLessAge(Byte age) {
        List<User> users = new ArrayList<>();
        List<Profile> profiles = profileRepository.findByAgeLessThan(age);

        for (Profile profile : profiles) {
            users.add(profile.getUser());
        }

        System.out.println(LOG_PATTERN + "findByLessAge");

        return users;
    }

    public List<User> findByAge(Byte age) {
        List<User> users = new ArrayList<>();
        List<Profile> profiles = profileRepository.findByAge(age);

        for (Profile profile : profiles) {
            users.add(profile.getUser());
        }

        System.out.println(LOG_PATTERN + "findByAge");

        return users;
    }

    public User findByPhone(String phone) {
        List<Phone> phones = phoneRepository.findByValue(phone);

        System.out.println(LOG_PATTERN + "findByPhone");

        for (Phone phoneObj : phones) {

            if (phoneObj.getValue().equals(phone)) {
                return phoneObj.getUser();
            }

        }

        return new User();
    }

    public List<User> findByName(String name) {
        System.out.println(LOG_PATTERN + "findByName");

        return userRepository.findByName(name);
    }

    public List<User> findByEmail(String email) {
        System.out.println(LOG_PATTERN + "findByEmail");

        return userRepository.findByEmail(email);
    }

    public boolean existsByPhone(String phoneValue) {
        List<Phone> phones = phoneRepository.findByValue(phoneValue);

        return phones.size() == 1;
    }
}
