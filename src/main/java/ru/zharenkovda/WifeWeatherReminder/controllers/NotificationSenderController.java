package ru.zharenkovda.WifeWeatherReminder.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.zharenkovda.WifeWeatherReminder.repository.WeatherRepository;
import ru.zharenkovda.WifeWeatherReminder.runnables.SmsRunnable;
import ru.zharenkovda.WifeWeatherReminder.runnables.TelegramRunnable;
import ru.zharenkovda.WifeWeatherReminder.runnables.TwitterRunnable;
import ru.zharenkovda.WifeWeatherReminder.runnables.WeatherRunnable;
import ru.zharenkovda.WifeWeatherReminder.services.NotificationService;
import ru.zharenkovda.WifeWeatherReminder.services.SchedulerService;
import ru.zharenkovda.WifeWeatherReminder.services.TwitterService;
import ru.zharenkovda.WifeWeatherReminder.services.WeatherService;

import java.util.concurrent.TimeUnit;

@RestController
@EnableAutoConfiguration
@RequestMapping("/")
public class NotificationSenderController {

    private static String twitterAuthPropsFileName = "twitter_oauth.properties";
    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationSenderController.class);

    @Autowired
    SchedulerService schedulerService;

    @Autowired
    WeatherRepository weatherRepository;

    @Autowired
    NotificationService notificationService;

    @Autowired
    WeatherService weatherService;

    @Autowired
    TwitterService twitterService;

    /**
     * main scenario. Sending forecast to bot conversation
     * @return
     */
    @RequestMapping("/start")
    public String startTelegramSending(){
        schedulerService.executeRunnableTask(new WeatherRunnable(weatherRepository,weatherService),0L,4L, TimeUnit.HOURS);
        schedulerService.executeRunnableTask(new TelegramRunnable(notificationService,weatherRepository.getWeatherString(),"126264498"),0L,1L, TimeUnit.MINUTES);
        return "OK";
    }

    /**
     * stop execution
     * @return
     */
    @RequestMapping("/stop")
    public String stopAll(){
        schedulerService.stopExecutorService();
        return "Scheduler is stopped";
    }

    /**
     * rerun scheduler
     * @return
     */
    @RequestMapping("/restart")
    public String startService(){
        schedulerService.startExecitorServiceAfterStop();
        return "Scheduler rerunned";
    }


    /*
    * separate starts
     */
    @RequestMapping("/weather/start")
    public String startWeatherPolling() {
        schedulerService.executeRunnableTask(new WeatherRunnable(weatherRepository,weatherService),0L,4L, TimeUnit.HOURS);
        return "OK";
    }

    @RequestMapping("/twitter/start")
    public String startDirectMessages(@RequestParam String username) {
        schedulerService.executeRunnableTask(new TwitterRunnable(twitterService,weatherRepository,username),0L,5L,TimeUnit.MINUTES);
        return "OK";
    }

    @RequestMapping("/sms/start")
    public String startSmsSending(){
        schedulerService.executeRunnableTask(new SmsRunnable(notificationService,weatherRepository.getWeatherString()),0L,2L, TimeUnit.HOURS);
        return "OK";
    }

    @RequestMapping("/weather")
    public String getWeather() {
        return weatherRepository.getWeatherString();

    }


}
