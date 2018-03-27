package ru.zharenkovda.YourHusbandBot.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.zharenkovda.YourHusbandBot.services.ResponseService;

@Component
public class PollingScheduler {

    @Autowired
    private ResponseService responseService;

    @Scheduled(cron = "0/15 * * * * *", zone = "Europe/Samara")
    public void pollTelegramChannel() {
        responseService.idleOrResponse();
    }
}
