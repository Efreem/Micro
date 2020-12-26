package ru.efreem.micro.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.efreem.micro.model.User;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByAgeGreaterThan(Byte age);
    List<User> findByAgeLessThan(Byte age);
}
