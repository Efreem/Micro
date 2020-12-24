package ru.efreem.micro.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.efreem.micro.model.Phone;

@Repository
public interface PhoneRepository extends CrudRepository<Phone, Long> {
}
