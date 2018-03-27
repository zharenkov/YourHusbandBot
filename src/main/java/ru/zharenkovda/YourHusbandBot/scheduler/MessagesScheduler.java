package ru.zharenkovda.YourHusbandBot.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.zharenkovda.YourHusbandBot.services.BotPhraseService;

import static ru.zharenkovda.YourHusbandBot.enumerations.WeatherType.TODAY;
import static ru.zharenkovda.YourHusbandBot.enumerations.WeatherType.TOMMOROW;

@Component
public class MessagesScheduler {

    @Autowired
    private BotPhraseService botPhraseService;


    @Scheduled(cron = "0 0 8 * * 1-5",zone = "Europe/Samara")
    @Scheduled(cron = "0 0 10 * * 6-7",zone = "Europe/Samara")
    public void sendTodayWeather() {
        botPhraseService.sendWeather(TODAY);
        System.out.println("Weather sending");
    }

    @Scheduled(cron = "0 0 22 * * *",zone = "Europe/Samara")
    public void sendTommorowWeather() {
        botPhraseService.sendWeather(TOMMOROW);
        System.out.println("Weather sending");
    }


    @Scheduled(cron = "0 30 11 * * 1-5",zone = "Europe/Samara")
    @Scheduled(cron = "0 30 15 * * 1-5",zone = "Europe/Samara")
    public void sayLovely() {
        botPhraseService.saySomethingLovely();
        System.out.println("Lovely sending");
    }


    @Scheduled(cron = "0 45 7 * * 1-5",zone = "Europe/Samara")
    public void sayGoodMorning() {
        botPhraseService.sayGoodMorining();
        System.out.println("GoodMorning sending");
    }


    @Scheduled(cron = "0 0 14 * * 1-5",zone = "Europe/Samara")
    public void sendCommon() {
        botPhraseService.sayCommon();
        System.out.println("Common sending");
    }


    @Scheduled(cron = "0 0 18 * * 1-5",zone = "Europe/Samara")
    public void sendTraffic() {
        botPhraseService.sendTraffic();
        System.out.println("Traffic sending");
    }

}
