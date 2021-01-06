package ru.efreem.micro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.efreem.micro.dto.ExceptionDto;
import ru.efreem.micro.model.User;
import ru.efreem.micro.service.user.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
public class UserCrudController {
    private UserService userService;

    private final static String CONTROLLER_LOG = "UserCrudController: executed method ";
    private static final String CONTROLLER_EXCEPTION_LOG = "UserCrudController exception in mapping ";

    @Autowired
    public UserCrudController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/findById")
    public Object findById(Long id) {
        Optional<User> user = userService.findById(id);

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
        List<User> users = userService.findByName(name);

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

        if (!userService.isCorrectEmail(email)) {
            ExceptionDto exception = new ExceptionDto();

            exception.setName("IncorrectEmail");
            exception.setDescription("Email " + email + " is not correct.");

            System.out.println(CONTROLLER_EXCEPTION_LOG + "IncorrectEmail");

            return exception;
        }

        user = userService.findByEmail(email);

        if (user.isEmpty()) {
            System.out.println(CONTROLLER_EXCEPTION_LOG + "findByEmail: UserNotFoundException");

            return ExceptionDto.generateUserNotFoundException("Sorry, but we cannot find user with email " +
                                                              email);
        }

        return user.get();
    }
}
