package ru.efreem.micro.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.efreem.micro.model.Profile;

@Repository
public interface ProfileRepository extends CrudRepository<Profile, Long> {
}
