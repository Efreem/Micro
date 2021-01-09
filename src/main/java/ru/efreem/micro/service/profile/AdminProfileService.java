package ru.efreem.micro.service.profile;

import ru.efreem.micro.model.Profile;

import java.math.BigDecimal;
import java.util.List;

public interface AdminProfileService {
    void updateCashById(BigDecimal cash, Long id);
    List<Profile> findAll();
}
