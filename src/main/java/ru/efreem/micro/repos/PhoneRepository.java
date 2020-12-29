package ru.efreem.micro.repos;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.efreem.micro.model.Phone;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface PhoneRepository extends CrudRepository<Phone, Long> {
    @Modifying
    @Query(value = "UPDATE Phone p SET p.value=?1 WHERE p.id=?2")
    void updateValueById(String value, Long id);

    List<Phone> findByValue(String value);
}
