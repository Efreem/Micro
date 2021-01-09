package ru.efreem.micro.service.profile;

import ru.efreem.micro.model.Profile;

import java.util.List;

public interface DefaultProfileService {
    List<Profile> findAll();
}
