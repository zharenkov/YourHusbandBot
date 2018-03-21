package ru.zharenkovda.WifeWeatherReminder.runnables;

import org.apache.commons.lang3.StringUtils;
import ru.zharenkovda.WifeWeatherReminder.services.TelegramService;

import java.io.IOException;
@Deprecated
public class TelegramRunnable implements Runnable {


    TelegramService telegramService;
    String message;
    String chatId;

    public TelegramRunnable(TelegramService telegramService, String message, String chatId) {
        this.telegramService = telegramService;
        this.message = message;
        this.chatId = chatId;
    }

    @Override
    public void run() {
        if (StringUtils.isNotEmpty(message)){
            try {
                telegramService.sendTelegramBotMessage(message,chatId);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
