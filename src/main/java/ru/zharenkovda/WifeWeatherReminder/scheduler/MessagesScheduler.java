package ru.zharenkovda.WifeWeatherReminder.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.zharenkovda.WifeWeatherReminder.services.BotPhraseService;

import static ru.zharenkovda.WifeWeatherReminder.enumerations.WeatherType.TODAY;
import static ru.zharenkovda.WifeWeatherReminder.enumerations.WeatherType.TOMMOROW;

@Component
public class MessagesScheduler {

    @Autowired
    private BotPhraseService botPhraseService;


    //@Scheduled(cron = "0/45 * * * * 1-6",zone = "Europe/Samara")
    public void sendTodayWeather() {
        botPhraseService.sendWeather(TODAY);
        System.out.println("Weather sending");
    }

    //@Scheduled(cron = "0/45 * * * * 1-6",zone = "Europe/Samara")
    public void sendTommorowWeather() {
        botPhraseService.sendWeather(TOMMOROW);
        System.out.println("Weather sending");
    }


    //@Scheduled(cron = "15/45 * * * * 1-5",zone = "Europe/Samara")
    public void sayLovely() {
        botPhraseService.saySomethingLovely();
        System.out.println("Lovely sending");
    }


    //@Scheduled(cron = "30/45 * * * * 1-5",zone = "Europe/Samara")
    public void sayGoodMorning() {
        botPhraseService.sayGoodMorining();
        System.out.println("GoodMorning sending");
    }


    //@Scheduled(cron = "45/45 * * * * 1-5",zone = "Europe/Samara")
    public void sendCommon() {
        botPhraseService.sayCommon();
        System.out.println("Common sending");
    }


    //@Scheduled(cron = "0/15 * * * * *",zone = "Europe/Samara")
    public void sendTraffic() {
        botPhraseService.sendTraffic();
        System.out.println("Traffic sending");
    }

}
