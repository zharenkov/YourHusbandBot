package ru.zharenkovda.WifeWeatherReminder.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.zharenkovda.WifeWeatherReminder.repository.WeatherRepository;
import ru.zharenkovda.WifeWeatherReminder.services.NotificationService;
import ru.zharenkovda.WifeWeatherReminder.services.TwitterService;
import ru.zharenkovda.WifeWeatherReminder.services.WeatherService;
import twitter4j.TwitterException;

import java.io.IOException;

@Component
public class ActionsScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActionsScheduler.class);

    @Autowired
    WeatherService weatherService;

    @Autowired
    NotificationService notificationService;

    @Autowired
    WeatherRepository weatherRepository;

    @Autowired
    TwitterService twitterService;

    @Scheduled(cron = "0 30 7 * * *")
    public void getWeather() {
        try {
            System.out.println("Weather getting");
            weatherRepository.setWeatherString(weatherService.getWeatherString());
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Scheduled(cron = "0 0 8 * * *")
    public void sendToTelegram() {
        try {
            System.out.println("Telegram sending");
            notificationService.sendTelegramBotMessage(weatherRepository.getWeatherString(), "126264498");
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Scheduled(cron = "0 0 8 * * *")
    public void sendToTwitter() {
        try {
            System.out.println("Twitter sending");
            twitterService.sendTwitterDirectMessage("zharenkov", weatherRepository.getWeatherString());
        } catch (TwitterException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Scheduled(cron = "0 0 8 * * *")
    public void sendToSms() {
        System.out.println("Sms sending");
        notificationService.sendTwilioSmsForecast(weatherRepository.getWeatherString());
    }
}
