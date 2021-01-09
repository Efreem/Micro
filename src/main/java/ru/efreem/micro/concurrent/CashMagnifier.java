package ru.efreem.micro.concurrent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.efreem.micro.model.Profile;
import ru.efreem.micro.service.profile.ProfileServiceImplementation;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CashMagnifier implements Runnable {
    @Autowired
    private ProfileServiceImplementation profileServiceImplementation;
    Map<Profile, Integer> cashPercentMapping;

    private static final String THREAD_LOG = "Cash magnifier: ";
    private static final BigDecimal MULTIPLE_NUM = new BigDecimal(0.1);
    private static final Integer MAXIMAL_PERCENT = 107;

    public CashMagnifier() {
    }

    @Autowired
    public CashMagnifier(ProfileServiceImplementation profileServiceImplementation) {
        this.profileServiceImplementation = profileServiceImplementation;
    }

    @Override
    public void run() {
        System.out.println("THREAD IS ON DUTY");
        List<Profile> profiles;
        cashPercentMapping = new HashMap<>();

        while(true) {
            profiles = profileServiceImplementation.findAll();

            if (profiles == null) {
                continue;
            }

            System.out.println(THREAD_LOG + "IS RUNNING!");

            //Регистрация новых профилей, если во время ожидания потока были добавлены новые профили
            profiles.stream()
                    .filter(profile -> !isIn(profile))
                    .forEach(profile -> cashPercentMapping.put(profile, 10));

            //Увеличение на 10% всех счётов, если их размер меньше 107% от изначального
            cashPercentMapping.keySet().stream()
                    .filter(profile -> cashPercentMapping.get(profile) < MAXIMAL_PERCENT)
                    .forEach(profile -> {
                        increaseCash(profile);
                        registerNewPercentValue(profile);
                    });

            //Перевод потока в режим ожидания на 20 секунд
            synchronized (this) {
                try {
                    this.wait(20000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /*
    Проверить, находится ли профиль в таблице профилей, учитывающей текущей процент, на который
    увеличен изначальный счёт
     */
    public boolean isIn(Profile profile) {

        for (Profile prof : cashPercentMapping.keySet()) {
            if (prof.equalsById(profile)) {
                return true;
            }
        }

        return false;
    }

    /*
    Увеличить счёт на 10%
    CASH = CASH + CASH * 0.1
     */
    public void increaseCash(Profile profile) {
        profile.setCash(profile.getCash().add(profile.getCash().multiply(MULTIPLE_NUM)));

        profileServiceImplementation.updateCashById(profile.getCash(), profile.getId());
    }

    //Обновить текущий процент, на который увеличен счёт профиля
    public void registerNewPercentValue(Profile profile) {
        cashPercentMapping.put(profile, cashPercentMapping.get(profile) + 10);
    }
}
