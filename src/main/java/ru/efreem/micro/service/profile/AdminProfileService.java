package ru.efreem.micro.service.profile;

import org.springframework.beans.factory.annotation.Autowired;
import ru.efreem.micro.model.Profile;
import ru.efreem.micro.repos.ProfileRepository;

import java.math.BigDecimal;
import java.util.List;

public class AdminProfileService implements AdminService, DefaultService {
    private ProfileRepository profileRepository;

    private final static String LOG_PATTERN = "EXECUTED METHOD: ";

    @Autowired
    public AdminProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public void updateCashById(BigDecimal cash, Long id) {
            profileRepository.updateCashById(cash, id);
            System.out.println(LOG_PATTERN + "updateCashById");
    }

    @Override
    public List<Profile> findAll() {
        return (List<Profile>)profileRepository.findAll();
    }
}
