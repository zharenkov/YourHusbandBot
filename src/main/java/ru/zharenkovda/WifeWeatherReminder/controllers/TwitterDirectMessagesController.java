package ru.zharenkovda.WifeWeatherReminder.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.zharenkovda.WifeWeatherReminder.repository.WeatherRepository;
import ru.zharenkovda.WifeWeatherReminder.runnables.TwitterRunnable;
import ru.zharenkovda.WifeWeatherReminder.services.SchedulerService;
import ru.zharenkovda.WifeWeatherReminder.utils.TwitterFactoryImpl;
import twitter4j.Twitter;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@RestController
@EnableAutoConfiguration
@RequestMapping("/twitter")
public class TwitterDirectMessagesController {
    private static String twitterAuthPropsFileName = "twitter_oauth.properties";

    private static final Logger LOGGER = LoggerFactory.getLogger(TwitterDirectMessagesController.class);

    @Autowired
    private WeatherRepository weatherRepository;

    @Autowired
    private SchedulerService schedulerService;


    @RequestMapping("/start")
    public String startDirectMessages(@RequestParam String username) {
        final Twitter twitter;
        try {
            twitter = TwitterFactoryImpl.getIntsance(twitterAuthPropsFileName);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(),e);
            return "FAILED";
        }
        schedulerService.executeRunnableTask(new TwitterRunnable(twitter,weatherRepository,username),5L,TimeUnit.MINUTES);
        return "OK";
    }
}
