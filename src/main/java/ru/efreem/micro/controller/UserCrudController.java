package ru.efreem.micro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.efreem.micro.dto.ExceptionDto;
import ru.efreem.micro.model.User;
import ru.efreem.micro.repos.UserRepository;
import ru.efreem.micro.service.user.AdminUserService;
import ru.efreem.micro.service.user.DefaultUserService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
@PreAuthorize("hasRole('ROLE_ADMIN')")
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
        this.userRepository = userRepository;
        this.adminUserService = adminUserService;
        this.defaultUserService = defaultUserService;
    }

    @PostMapping("findAll")
    public Object findAll() {
        return userRepository.findAll();
    }

    @PostMapping("/findById/{id}")
    public Object findById(@PathVariable(name = "id") Long id) {
        Optional<User> user = defaultUserService.findById(id);

        System.out.println(CONTROLLER_LOG + "findById");

        if (user.isEmpty()) {
            System.out.println(CONTROLLER_EXCEPTION_LOG + "findById: UserNotFoundException");

            return ExceptionDto.generateUserNotFoundException("Sorry, but we can't find user with id "
                                                               + id.toString());
        }

        return user.get();
    }

    @PostMapping("/findByName/{name}")
    public Object findByName(@PathVariable(name = "name") String name) {
        List<User> users = defaultUserService.findByName(name);

        System.out.println(CONTROLLER_LOG + "findByName");

        if (users == null) {
            System.out.println(CONTROLLER_EXCEPTION_LOG + "findByName: UserNotFoundException");

            return ExceptionDto.generateUserNotFoundException("Sorry, but we cannot find user with name " +
                                                              name);
        }

        return users;
    }

    @PostMapping("/findByEmail/{email}")
    public Object findByEmail(@PathVariable(name = "email") String email) {
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

    @PostMapping("/findByAge/{age}")
    public Object findByAge(@PathVariable(name = "age") Byte age) {
        List<User> users = defaultUserService.findByAge(age);

        if (users == null) {
            System.out.println(CONTROLLER_EXCEPTION_LOG + "findByAge:UserNotFoundException");

            return ExceptionDto.generateUserNotFoundException("Sorry, but we cannot find users with age " +
                                                               age.toString());
        }

        return users;
    }

    @PostMapping("/findByAgeLessThan/{age}")
    public Object findByAgeLessThan(@PathVariable(name = "age") Byte age) {
        List<User> users = defaultUserService.findByAgeLessThan(age);

        if (users == null) {
            System.out.println(CONTROLLER_EXCEPTION_LOG + "findByAgeLessThan:UserNotFoundException");

            return ExceptionDto.generateUserNotFoundException("Sorry, but we cannot find users with age " +
                    age.toString());
        }

        return users;
    }

    @PostMapping("/findByAgeGreaterThan/{age}")
    public Object findByAgeGreaterThan(@PathVariable(name = "age") Byte age) {
        List<User> users = defaultUserService.findByAgeGreaterThan(age);

        if (users == null) {
            System.out.println(CONTROLLER_EXCEPTION_LOG + "findByAgeGreaterThan:UserNotFoundException");

            return ExceptionDto.generateUserNotFoundException("Sorry, but we cannot find users with age " +
                    age.toString());
        }

        return users;
    }

    @PostMapping("/findByPhoneValue/{phoneValue}")
    public Object findByPhoneValue(@PathVariable(name = "phoneValue") String phoneValue) {
        List<User> users = defaultUserService.findByPhoneValue(phoneValue);

        if (users == null) {
            System.out.println(CONTROLLER_EXCEPTION_LOG + "findByPhoneValue:UserNotFoundException");

            return ExceptionDto.generateUserNotFoundException("Sorry, but we cannot find users with phoneValue " +
                    phoneValue);
        }

        return users;
    }

    @PostMapping("/save")
    public Object saveUser(String name, String email, String phoneValue, BigDecimal cash, Byte age) {
        return adminUserService.saveUser(name, email, phoneValue, cash, age);
    }

    @PostMapping("/updateEmailById/{id}/{email}")
    public Object updateEmailById(@PathVariable(name = "id") Long id,
                                  @PathVariable(name = "email") String email) {

        System.out.println(CONTROLLER_LOG + "updateEmailById");

        if (!adminUserService.isCorrectEmail(email)) {
            ExceptionDto exception = new ExceptionDto();

            exception.setName("IncorrectEmail");
            exception.setDescription("Sorry, but email " + email + " is not correct");

            System.out.println(CONTROLLER_EXCEPTION_LOG + "updateEmailById: IncorrectEmail");

            return exception;
        }

        if (adminUserService.isEmailExists(email)) {
            ExceptionDto exception = new ExceptionDto();

            exception.setName("EmailExists");
            exception.setDescription("Sorry, but we have email " + email);

            System.out.println(CONTROLLER_EXCEPTION_LOG + "updateEmailById: EmailExists");

            return exception;
        }

        return adminUserService.updateEmailById(email, id);
    }

    @PostMapping("/updateNameById/{id}/{name}")
    public Object updateNameById(@PathVariable(name = "id") Long id,
                                 @PathVariable(name = "name") String name) {
        System.out.println(CONTROLLER_LOG + "updateNameById");

        return adminUserService.updateNameById(name, id);
    }

}
