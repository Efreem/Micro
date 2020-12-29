package ru.efreem.micro.service.user;

import ru.efreem.micro.model.Phone;
import ru.efreem.micro.model.Profile;

import java.math.BigDecimal;

public interface AdminService {
    void updateProfileCashById(BigDecimal cash, Long id);
    void updateProfileAgeById(Profile profile, Byte age);
    void updatePhoneValueById(Phone phone, String value);
    void updateNameById(String name, Long id);
    void updateEmailById(String email, Long id);
}
