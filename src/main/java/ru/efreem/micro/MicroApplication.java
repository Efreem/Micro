package ru.efreem.micro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import ru.efreem.micro.concurrent.CashMagnifier;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class MicroApplication extends SpringBootServletInitializer {
    private static CashMagnifier cashMagnifier;

    @Autowired
    public MicroApplication(CashMagnifier cashMagnifier) {
        this.cashMagnifier = cashMagnifier;
    }

    public static void main(String[] args) {
        SpringApplication.run(MicroApplication.class);
        Thread cashMagnifierThread = new Thread(cashMagnifier);
        cashMagnifierThread.start();
    }

}
