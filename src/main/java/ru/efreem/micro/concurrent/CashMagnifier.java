package ru.efreem.micro.concurrent;

import org.springframework.beans.factory.annotation.Autowired;
import ru.efreem.micro.model.Profile;
import ru.efreem.micro.repos.ProfileRepository;
import ru.efreem.micro.service.profile.AdminProfileService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class CashMagnifier extends Thread {
    private AdminProfileService adminProfileService;
    Map<Profile, Integer> cashPercentMapping;

    private static final String THREAD_LOG = "Cash magnifier: ";
    private static final BigDecimal MULTIPLE_NUM = new BigDecimal(0.1);
    private static final Integer MAXIMAL_PERCENT = 107;

    @Autowired
    public CashMagnifier(AdminProfileService adminProfileService) {
        this.adminProfileService = adminProfileService;
    }

    @Override
    public void run() {
        System.out.println("THREAD IS ON DUTY");
        List<Profile> profiles;

        while(true) {
            profiles = adminProfileService.findAll();

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
            try {
                this.wait(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
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

        adminProfileService.updateCashById(profile.getCash(), profile.getId());
    }

    //Обновить текущий процент, на который увеличен счёт профиля
    public void registerNewPercentValue(Profile profile) {
        cashPercentMapping.put(profile, cashPercentMapping.get(profile) + 10);
    }

}
