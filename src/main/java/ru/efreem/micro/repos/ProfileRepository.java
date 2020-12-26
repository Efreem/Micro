package ru.efreem.micro.repos;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.efreem.micro.model.Profile;

import java.math.BigDecimal;

@Repository
public interface ProfileRepository extends CrudRepository<Profile, Long> {
    @Modifying
    @Query(value = "UPDATE Profile p SET p.cash=?1 WHERE p.id=?2")
    void updateCashById(BigDecimal cash, Long id);
}
