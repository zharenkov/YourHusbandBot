package ru.zharenkovda.WifeWeatherReminder.scheduler;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.zharenkovda.WifeWeatherReminder.BotPhraseDAO;
import ru.zharenkovda.WifeWeatherReminder.BotPhraseEntity;
import ru.zharenkovda.WifeWeatherReminder.repository.DataRepository;
import ru.zharenkovda.WifeWeatherReminder.services.TelegramService;
import ru.zharenkovda.WifeWeatherReminder.services.SmsService;
import ru.zharenkovda.WifeWeatherReminder.services.TwitterService;
import ru.zharenkovda.WifeWeatherReminder.services.WeatherService;
import twitter4j.TwitterException;

import java.io.IOException;
import java.util.Date;

@Component
public class ActionsScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActionsScheduler.class);

    @Autowired
    WeatherService weatherService;

    @Autowired
    TelegramService telegramService;

    @Autowired
    DataRepository dataRepository;

    @Autowired
    TwitterService twitterService;

    @Autowired
    SmsService smsService;

    //
    // @Scheduled(cron = "0/5 * * * * 1-5",zone = "Europe/Samara")
    public void getWeather() {
        try {
            System.out.println("Weather getting");
            dataRepository.setWeatherString(weatherService.getWeatherString());
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    //@Scheduled(cron = "2/5 * * * * 1-5",zone = "Europe/Samara")
    public void sendToTelegram() {
        try {
            System.out.println("Telegram sending");
            telegramService.sendTelegramBotMessage(dataRepository.getWeatherString(), dataRepository.getTelegramChatId());
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

   // @Scheduled(cron = "2/5 * * * * 1-5",zone = "Europe/Samara")
    public void sendToTwitter() {
        if (StringUtils.isNotEmpty(dataRepository.getTwitterUserName())) {
            try {
                System.out.println("Twitter sending");
                twitterService.sendTwitterDirectMessage(dataRepository.getTwitterUserName(), dataRepository.getWeatherString());
            } catch (TwitterException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }

    //@Scheduled(cron = "0 * * * * 1-5",zone = "Europe/Samara")
    public void sendToSms() {
        if (StringUtils.isNotEmpty(dataRepository.getPhoneNumber())) {
            System.out.println("Sms sending");
            smsService.sendTwilioSmsForecast(dataRepository.getPhoneNumber(), dataRepository.getWeatherString());
        }
    }

   // @Scheduled(cron = "0/15 * * * * *",zone = "Europe/Samara")
    public void pollTelegramChannel() throws IOException {
        Long currentDate =System.currentTimeMillis()/1000;
        Long lastUpdateDate = telegramService.getUpdateTime();
        if (currentDate - lastUpdateDate < 15){
            telegramService.sendTelegramBotMessage("resp",dataRepository.getTelegramChatId());
            System.out.println("resp sended");
        }

    }

    @Autowired
    BotPhraseDAO botPhraseDAO;
    @Scheduled(cron = "0/30 * * * * *")
    public  void getAllPhrases(){
        for (BotPhraseEntity b : botPhraseDAO.getMornignPhrases()){
            System.out.println(b.toString());
        }
    }
}
