package ru.efreem.micro.repos;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.efreem.micro.model.Profile;
import ru.efreem.micro.model.User;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProfileRepository extends CrudRepository<Profile, Long> {
    @Modifying
    @Query(value = "UPDATE Profile p SET p.cash=?1 WHERE p.id=?2")
    void updateCashById(BigDecimal cash, Long id);
    @Modifying
    @Query(value = "UPDATE Profile p SET p.age=?1 WHERE p.id=?2")
    void updateAgeById(Byte age, Long id);

    List<Profile> findByAgeGreaterThan(Byte age);
    List<Profile> findByAgeLessThan(Byte age);
    List<Profile> findByAge(Byte age);
}
