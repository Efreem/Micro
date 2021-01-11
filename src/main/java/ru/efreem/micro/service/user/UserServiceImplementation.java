package ru.efreem.micro.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.efreem.micro.dto.ActionDto;
import ru.efreem.micro.dto.ExceptionDto;
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
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserServiceImplementation implements AdminUserService, DefaultUserService {
    private UserRepository userRepository;
    private ProfileRepository profileRepository;
    private PhoneRepository phoneRepository;

    private static final String LOG_PATTERN = "EXECUTED METHOD: ";

    @Autowired
    public UserServiceImplementation(UserRepository userRepository,
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
    public Object updateNameById(String name, Long id) {
        ActionDto action = new ActionDto();

        userRepository.updateNameById(name, id);

        System.out.println(LOG_PATTERN + "updateNameById");

        action.setMethod("updateNameById");
        action.setDescription("Successfully completed");

        return action;
    }

    @Override
    public Object updateEmailById(String email, Long id) {
        ActionDto action = new ActionDto();

        if (isCorrectEmail(email) || !isEmailExists(email)) {
            userRepository.updateEmailById(email, id);
        }

        System.out.println(LOG_PATTERN + "updateEmailById");

        action.setMethod("updateEmailById");
        action.setDescription("Successfully completed");

        return action;
    }

    @Override
    public List<User> findByAge(Byte age) {
        return mapProfilesToUsers(profileRepository.findByAge(age).stream());
    }

    @Override
    public List<User> findByAgeGreaterThan(Byte age) {
        return mapProfilesToUsers(profileRepository.findByAgeGreaterThan(age).stream());
    }

    @Override
    public List<User> findByAgeLessThan(Byte age) {
        return mapProfilesToUsers(profileRepository.findByAgeLessThan(age).stream());
    }

    @Override
    public List<User> findByPhoneValue(String phoneValue) {
        return phoneRepository.findByValue(phoneValue).stream()
                .map(phone -> phone.getUser())
                .collect(Collectors.toList());
    }

    @Override
    public Object saveUser(String name, String email, String phoneValue, BigDecimal cash, Byte age) {

        User user = new User();
        Phone phone = new Phone();
        Profile profile = new Profile();

        user.setName(name);

        if (isCorrectEmail(email) || isEmailExists(email)) {
            user.setEmail(email);
        } else {
            ExceptionDto exceptionDto = new ExceptionDto();

            exceptionDto.setName("IncorrectEmail");
            exceptionDto.setDescription("Email " + email + " is not correct!");

            return exceptionDto;
        }

        phone.setValue(phoneValue);
        profile.setCash(cash);
        profile.setAge(age);

        phone.setUser(user);
        profile.setUser(user);

        userRepository.save(user);

        ActionDto actionDto = new ActionDto();

        actionDto.setMethod("saveUser");
        actionDto.setDescription("User successfully saved!");

        return actionDto;
    }

    public boolean isCorrectEmail(String email) {
        return email.matches("[a-zA-Z0-9]*@[a-zA-Z0-9]*\\.[a-z]*");
    }

    public boolean isEmailExists(String email) {
        return userRepository.findByEmail(email).isEmpty();
    }

    private List<User> mapProfilesToUsers(Stream<Profile> profileStream) {
        return profileStream
                .map(profile -> profile.getUser())
                .collect(Collectors.toList());
    }

}
