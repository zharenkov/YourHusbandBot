package ru.zharenkovda.WifeWeatherReminder.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.zharenkovda.WifeWeatherReminder.repository.DataRepository;
import ru.zharenkovda.WifeWeatherReminder.services.BotPhraseService;
import ru.zharenkovda.WifeWeatherReminder.services.TelegramService;

//@Component
public class PollingScheduler {

    @Autowired
    TelegramService telegramService;

    @Autowired
    BotPhraseService botPhraseService;

    @Scheduled(cron = "0/15 * * * * *",zone = "Europe/Samara")
    public void pollTelegramChannel() {
        Long currentDate =System.currentTimeMillis()/1000;
        Long lastUpdateDate = telegramService.getUpdateTime();
        if (currentDate - lastUpdateDate < 16){
            botPhraseService.saySomethingLovely();
        }
    }
}