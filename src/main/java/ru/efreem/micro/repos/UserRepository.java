package ru.efreem.micro.repos;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.efreem.micro.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Modifying
    @Query(value = "UPDATE User u SET u.name=?1 WHERE u.id=?2")
    void updateNameById(String name, Long id);
    @Modifying
    @Query(value = "UPDATE User u SET u.email=?1 WHERE u.id=?2")
    void updateEmailById(String email, Long id);

    List<User> findByName(String name);
    Optional<User> findByEmail(String email);
}
