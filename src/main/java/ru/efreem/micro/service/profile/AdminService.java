package ru.efreem.micro.service.profile;

import java.math.BigDecimal;

public interface AdminService {
    void updateCashById(BigDecimal cash, Long id);
}
