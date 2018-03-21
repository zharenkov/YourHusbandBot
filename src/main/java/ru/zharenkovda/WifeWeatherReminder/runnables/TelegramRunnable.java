package ru.zharenkovda.WifeWeatherReminder.runnables;

import org.apache.commons.lang3.StringUtils;
import ru.zharenkovda.WifeWeatherReminder.services.NotificationService;

import java.io.IOException;
@Deprecated
public class TelegramRunnable implements Runnable {


    NotificationService notificationService;
    String message;
    String chatId;

    public TelegramRunnable(NotificationService notificationService, String message,String chatId) {
        this.notificationService = notificationService;
        this.message = message;
        this.chatId = chatId;
    }

    @Override
    public void run() {
        if (StringUtils.isNotEmpty(message)){
            try {
                notificationService.sendTelegramBotMessage(message,chatId);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
