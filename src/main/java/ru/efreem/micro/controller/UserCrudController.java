package ru.efreem.micro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.efreem.micro.dto.ExceptionDto;
import ru.efreem.micro.model.User;
import ru.efreem.micro.service.user.AdminUserService;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
public class UserCrudController {
    private AdminUserService adminUserService;

    private final static String CONTROLLER_LOG = "UserCrudController: executed method ";
    private static final String CONTROLLER_EXCEPTION_LOG = "UserCrudController exception: ";

    @Autowired
    public UserCrudController(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }

    @GetMapping("/findById")
    public Object findById(Long id) {
        Optional<User> user = adminUserService.findById(id);

        System.out.println(CONTROLLER_LOG + "findById");

        if (user.isEmpty()) {
            ExceptionDto exception = new ExceptionDto();
            System.out.println(CONTROLLER_EXCEPTION_LOG + "userNotFoundException");

            exception.setName("userNotFoundException");
            exception.setDescription("Sorry, but we can't find user with id: " + id.toString());

            return exception;
        }

        return user.get();
    }


}
