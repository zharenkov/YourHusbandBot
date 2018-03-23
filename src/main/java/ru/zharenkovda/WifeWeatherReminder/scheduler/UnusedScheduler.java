package ru.zharenkovda.WifeWeatherReminder.scheduler;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import ru.zharenkovda.WifeWeatherReminder.repository.DataRepository;
import ru.zharenkovda.WifeWeatherReminder.services.SmsService;
import ru.zharenkovda.WifeWeatherReminder.services.TwitterService;
import twitter4j.TwitterException;

//@Component
public class UnusedScheduler {


    @Autowired
    TwitterService twitterService;

    @Autowired
    SmsService smsService;

    @Autowired
    DataRepository dataRepository;

    // @Scheduled(cron = "2/5 * * * * 1-5",zone = "Europe/Samara")
    public void sendToTwitter() {
        if (StringUtils.isNotEmpty(dataRepository.getTwitterUserName())) {
            try {
                System.out.println("Twitter sending");
                twitterService.sendTwitterDirectMessage(dataRepository.getTwitterUserName(), dataRepository.getTodayWeatherString());
            } catch (TwitterException e) {
                System.out.println("Unable to send to twitter");
            }
        }
    }

    //@Scheduled(cron = "0 * * * * 1-5",zone = "Europe/Samara")
    public void sendToSms() {
        if (StringUtils.isNotEmpty(dataRepository.getPhoneNumber())) {
            System.out.println("Sms sending");
            smsService.sendTwilioSmsForecast(dataRepository.getPhoneNumber(), dataRepository.getTodayWeatherString());
        }
    }
}
