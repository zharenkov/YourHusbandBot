package ru.zharenkovda.WifeWeatherReminder.controllers;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.zharenkovda.WifeWeatherReminder.utils.TwitterFactoryImpl;
import ru.zharenkovda.WifeWeatherReminder.exceptions.TwitterInitializationException;
import twitter4j.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@RestController
@EnableAutoConfiguration
public class TwitterAutoController {

    private ScheduledThreadPoolExecutor scheduledExecutorService = new ScheduledThreadPoolExecutor(10);
    private static String twitterAuthPropsFileName = "twitter_oauth.properties";

    @RequestMapping("/start")
    public String startTweets() {
        final Twitter twitter;
        try {
            twitter = TwitterFactoryImpl.getIntsance(twitterAuthPropsFileName);
        } catch (TwitterInitializationException e){
            return e.getMessage();
        }
        scheduledExecutorService.scheduleWithFixedDelay(() -> {
            try {
                twitter.tweets().updateStatus("Automatic tweet at "+ new SimpleDateFormat("hh:mm:ss").format(new Date()));
            } catch (TwitterException e) {
                e.printStackTrace();
            }
        },0,24, TimeUnit.HOURS);

        return "Autotweeting started";
    }

    @RequestMapping("/stop")
    public String stopTweeting(){
        scheduledExecutorService.getQueue().clear();
        scheduledExecutorService.shutdown();
        return "Autotweeting stoped";

    }
}
