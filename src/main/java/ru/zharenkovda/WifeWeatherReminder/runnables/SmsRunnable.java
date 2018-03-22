package ru.zharenkovda.WifeWeatherReminder.runnables;

import org.apache.commons.lang3.StringUtils;
import ru.zharenkovda.WifeWeatherReminder.services.SmsService;

@Deprecated
public class SmsRunnable implements Runnable {

    SmsService smsService;
    String message;
    String phoneNumber;

    public SmsRunnable(SmsService smsService,String phoneNumber, String message) {
        this.smsService = smsService;
        this.message = message;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void run() {
        if (StringUtils.isNotEmpty(message)){
            smsService.sendTwilioSmsForecast(phoneNumber,message);
        }
    }
}
