package ru.efreem.micro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import ru.efreem.micro.concurrent.CashMagnifier;
import ru.efreem.micro.service.user.AdminUserService;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.math.BigDecimal;

@SpringBootApplication
@EnableSwagger2
public class MicroApplication extends SpringBootServletInitializer {
    private static CashMagnifier cashMagnifier;
    private static AdminUserService adminUserService;

    @Autowired
    public MicroApplication(CashMagnifier cashMagnifier,
                            AdminUserService adminUserService) {
        this.cashMagnifier = cashMagnifier;
        this.adminUserService = adminUserService;
    }

    public static void main(String[] args) {
        SpringApplication.run(MicroApplication.class);
        Thread cashMagnifierThread = new Thread(cashMagnifier);
        cashMagnifierThread.start();
        //adminUserService.saveUser("1234", "exal@gmail.com", "+77777777777", new BigDecimal(32),(byte)3);
    }



}
