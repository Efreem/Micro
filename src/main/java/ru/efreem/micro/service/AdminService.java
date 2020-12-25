package ru.efreem.micro.service;

import java.math.BigDecimal;

public interface AdminService {
    void updateProfileCashById(BigDecimal cash, Long id);
}
