package ru.efreem.micro.concurrent;

import org.springframework.beans.factory.annotation.Autowired;
import ru.efreem.micro.model.Profile;
import ru.efreem.micro.repos.ProfileRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class CashMagnifier extends Thread {
    private ProfileRepository profileRepository;
    Map<Profile, Integer> cashPercentMapping;

    private static final String THREAD_LOG = "Cash magnifier: ";
    private static final BigDecimal MULTIPLE_NUM = new BigDecimal(0.1);
    private static final Integer MAXIMAL_PERCENT = 107;

    @Autowired
    public CashMagnifier(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public void run() {
        System.out.println("THREAD IS ON DUTY");
        List<Profile> profiles;

        while(true) {
            profiles = (List<Profile>)profileRepository.findAll();

            System.out.println(THREAD_LOG + "IS RUNNING!");

            //Регистрация новых профилей, если во время ожидания потока были добавлены новые профили
            for (Profile profile : profiles) {
                if (!isIn(profile)) {
                    cashPercentMapping.put(profile, 10);
                }
            }

            for (Profile profile : cashPercentMapping.keySet()) {
                if (cashPercentMapping.get(profile) < MAXIMAL_PERCENT) {
                    increaseCash(profile);
                    registerNewPercentValue(profile);
                }
            }

            try {
                this.wait(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    //Проверить, находится ли профиль в таблице профилей, учитывающей текущей процент, на который
    //увеличен изначальный счёт
    public boolean isIn(Profile profile) {

        for (Profile prof : cashPercentMapping.keySet()) {
            if (prof.equalsById(profile)) {
                return true;
            }
        }

        return false;
    }

    //Увеличить счёт на 10%
    //CASH = CASH + CASH * 0.1
    public void increaseCash(Profile profile) {
        profile.setCash(profile.getCash().add(profile.getCash().multiply(MULTIPLE_NUM)));
    }

    //Обновить текущий процент, на который увеличен счёт профиля
    public void registerNewPercentValue(Profile profile) {
        cashPercentMapping.put(profile, cashPercentMapping.get(profile) + 10);
    }

}
