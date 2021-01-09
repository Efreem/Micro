package ru.efreem.micro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.efreem.micro.dto.ActionDto;
import ru.efreem.micro.dto.ExceptionDto;
import ru.efreem.micro.model.Phone;
import ru.efreem.micro.model.Profile;
import ru.efreem.micro.model.User;
import ru.efreem.micro.repos.UserRepository;
import ru.efreem.micro.service.user.AdminUserService;
import ru.efreem.micro.service.user.DefaultUserService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
public class UserCrudController {
    private UserRepository userRepository;
    private AdminUserService adminUserService;
    private DefaultUserService defaultUserService;

    private final static String CONTROLLER_LOG = "UserCrudController: executed method ";
    private static final String CONTROLLER_EXCEPTION_LOG = "UserCrudController exception in mapping ";

    @Autowired
    public UserCrudController(UserRepository userRepository,
                              AdminUserService adminUserService,
                              DefaultUserService defaultUserService) {
        this.adminUserService = adminUserService;
        this.defaultUserService = defaultUserService;
    }

    @GetMapping("/findById")
    public Object findById(Long id) {
        Optional<User> user = defaultUserService.findById(id);

        System.out.println(CONTROLLER_LOG + "findById");

        if (user.isEmpty()) {
            System.out.println(CONTROLLER_EXCEPTION_LOG + "findById: UserNotFoundException");

            return ExceptionDto.generateUserNotFoundException("Sorry, but we can't find user with id "
                                                               + id.toString());
        }

        return user.get();
    }

    @GetMapping("/findByName")
    public Object findByName(String name) {
        List<User> users = defaultUserService.findByName(name);

        System.out.println(CONTROLLER_LOG + "findByName");

        if (users == null) {
            System.out.println(CONTROLLER_EXCEPTION_LOG + "findByName: UserNotFoundException");

            return ExceptionDto.generateUserNotFoundException("Sorry, but we cannot find user with name " +
                                                              name);
        }

        return users;
    }

    @GetMapping("/findByEmail")
    public Object findByEmail(String email) {
        Optional<User> user;

        System.out.println(CONTROLLER_LOG + "findByEmail");

        if (!defaultUserService.isCorrectEmail(email)) {
            ExceptionDto exception = new ExceptionDto();

            exception.setName("IncorrectEmail");
            exception.setDescription("Email " + email + " is not correct.");

            System.out.println(CONTROLLER_EXCEPTION_LOG + "IncorrectEmail");

            return exception;
        }

        user = defaultUserService.findByEmail(email);

        if (user.isEmpty()) {
            System.out.println(CONTROLLER_EXCEPTION_LOG + "findByEmail: UserNotFoundException");

            return ExceptionDto.generateUserNotFoundException("Sorry, but we cannot find user with email " +
                                                              email);
        }

        return user.get();
    }

    @GetMapping("/findByAge")
    public Object findByAge(Byte age) {
        List<User> users = defaultUserService.findByAge(age);

        if (users == null) {
            System.out.println(CONTROLLER_EXCEPTION_LOG + "findByAge:UserNotFoundException");

            return ExceptionDto.generateUserNotFoundException("Sorry, but we cannot find users with age " +
                                                               age.toString());
        }

        return users;
    }

    @GetMapping("/findByAgeLessThan")
    public Object findByAgeLessThan(Byte age) {
        List<User> users = defaultUserService.findByAgeLessThan(age);

        if (users == null) {
            System.out.println(CONTROLLER_EXCEPTION_LOG + "findByAgeLessThan:UserNotFoundException");

            return ExceptionDto.generateUserNotFoundException("Sorry, but we cannot find users with age " +
                    age.toString());
        }

        return users;
    }

    @GetMapping("/findByAgeGreaterThan")
    public Object findByAgeGreaterThan(Byte age) {
        List<User> users = defaultUserService.findByAgeGreaterThan(age);

        if (users == null) {
            System.out.println(CONTROLLER_EXCEPTION_LOG + "findByAgeGreaterThan:UserNotFoundException");

            return ExceptionDto.generateUserNotFoundException("Sorry, but we cannot find users with age " +
                    age.toString());
        }

        return users;
    }

    @GetMapping("/findByPhoneValue")
    public Object findByPhoneValue(String phoneValue) {
        List<User> users = defaultUserService.findByPhoneValue(phoneValue);

        if (users == null) {
            System.out.println(CONTROLLER_EXCEPTION_LOG + "findByPhoneValue:UserNotFoundException");

            return ExceptionDto.generateUserNotFoundException("Sorry, but we cannot find users with phoneValue " +
                    phoneValue);
        }

        return users;
    }

    @GetMapping("/save")
    public Object saveUser(String name, String email, String phoneValue, BigDecimal cash, Byte age) {
        return adminUserService.saveUser(name, email, phoneValue, cash, age);
    }

}
