package ru.zharenkovda.WifeWeatherReminder.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.zharenkovda.WifeWeatherReminder.exceptions.TwitterInitializationException;
import ru.zharenkovda.WifeWeatherReminder.repository.WeatherRepository;
import ru.zharenkovda.WifeWeatherReminder.runnables.TwitterRunnable;
import ru.zharenkovda.WifeWeatherReminder.services.SchedulerService;
import ru.zharenkovda.WifeWeatherReminder.utils.TwitterFactoryImpl;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@RestController
@EnableAutoConfiguration
@RequestMapping("/twitter")
public class TwitterDirectMessagesController {
    private static String twitterAuthPropsFileName = "twitter_oauth.properties";

    @Autowired
    private WeatherRepository weatherRepository;

    @Autowired
    private SchedulerService schedulerService;


    @RequestMapping("/start")
    public String startDirectMessages(@RequestParam String username) {
        final Twitter twitter;
        try {
            twitter = TwitterFactoryImpl.getIntsance(twitterAuthPropsFileName);
        } catch (TwitterInitializationException e){
            return e.getMessage();
        }
        schedulerService.executeRunnableTask(new TwitterRunnable(twitter,weatherRepository,username),5L,TimeUnit.MINUTES);
        return "Autotweeting started";
    }
}
