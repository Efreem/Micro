package ru.efreem.micro.service.user;

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
import java.util.Optional;

@Service
public class AdminUserService implements AdminService, DefaultService {
    private UserRepository userRepository;
    private ProfileRepository profileRepository;
    private PhoneRepository phoneRepository;

    private static final String LOG_PATTERN = "EXECUTED METHOD: ";

    @Autowired
    public AdminUserService(UserRepository userRepository,
                            ProfileRepository profileRepository,
                            PhoneRepository phoneRepository) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
        this.phoneRepository = phoneRepository;
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void updateProfileCashById(BigDecimal cash, Long id) {
        profileRepository.updateCashById(cash, id);
        System.out.println(LOG_PATTERN + "updateCashById");
    }

    @Override
    public List<User> findByGreaterAge(Byte age) {
        List<User> users = new ArrayList<>();
        List<Profile> profiles = profileRepository.findByAgeGreaterThan(age);

        for (Profile profile : profiles) {
            users.add(profile.getUser());
        }

        System.out.println(LOG_PATTERN + "findByGreaterAge");

        return users;
    }

    @Override
    public List<User> findByLessAge(Byte age) {
        List<User> users = new ArrayList<>();
        List<Profile> profiles = profileRepository.findByAgeLessThan(age);

        for (Profile profile : profiles) {
            users.add(profile.getUser());
        }

        System.out.println(LOG_PATTERN + "findByLessAge");

        return users;
    }

    @Override
    public List<User> findByAge(Byte age) {
        List<User> users = new ArrayList<>();
        List<Profile> profiles = profileRepository.findByAge(age);

        for (Profile profile : profiles) {
            users.add(profile.getUser());
        }

        System.out.println(LOG_PATTERN + "findByAge");

        return users;
    }

    @Override
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

    @Override
    public List<User> findByName(String name) {
        System.out.println(LOG_PATTERN + "findByName");

        return userRepository.findByName(name);
    }

    @Override
    public List<User> findByEmail(String email) {
        System.out.println(LOG_PATTERN + "findByEmail");

        return userRepository.findByEmail(email);
    }

    public boolean existsByPhone(String phoneValue) {
        List<Phone> phones = phoneRepository.findByValue(phoneValue);

        return phones.size() == 1;
    }

    public boolean isCorrectPhoneValue(String value) {
        return value.matches("\\+?[78][0-9]{10}");
    }

    public boolean isCorrectEmail(String email) {
            return email.matches("[a-zA-Z0-9]*@[a-zA-Z0-9]*\\.[a-z]*");
    }

    @Override
    public void updateProfileAgeById(Profile profile, Byte age) {
        profileRepository.updateAgeById(age, profile.getId());

        System.out.println(LOG_PATTERN + "updateProfileAgeById");
    }

    @Override
    public void updatePhoneValueById(Phone phone, String value) {

        if (isCorrectPhoneValue(value)) {
            phoneRepository.updateValueById(value, phone.getId());
        }

        System.out.println(LOG_PATTERN + "updatePhoneValueById");
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
}
