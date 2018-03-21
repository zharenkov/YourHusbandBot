package ru.zharenkovda.WifeWeatherReminder.runnables;

import org.apache.commons.lang3.StringUtils;
import ru.zharenkovda.WifeWeatherReminder.services.NotificationService;

@Deprecated
public class SmsRunnable implements Runnable {

    NotificationService notificationService;
    String message;

    public SmsRunnable(NotificationService notificationService, String message) {
        this.notificationService = notificationService;
        this.message = message;
    }

    @Override
    public void run() {
        if (StringUtils.isNotEmpty(message)){
            notificationService.sendTwilioSmsForecast(message);
        }
    }
}
