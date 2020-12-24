package ru.efreem.micro.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.efreem.micro.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
