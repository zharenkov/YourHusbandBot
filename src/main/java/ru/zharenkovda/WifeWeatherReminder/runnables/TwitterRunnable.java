package ru.zharenkovda.WifeWeatherReminder.runnables;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.zharenkovda.WifeWeatherReminder.repository.WeatherRepository;
import ru.zharenkovda.WifeWeatherReminder.services.TwitterService;
import twitter4j.Twitter;
import twitter4j.TwitterException;

@Deprecated
public class TwitterRunnable implements Runnable {
    private static final Logger LOGGER= LoggerFactory.getLogger(TwitterRunnable.class);

    private TwitterService twitterService;
    private WeatherRepository repository;
    private String username;

    public TwitterRunnable(TwitterService twitterService, WeatherRepository repository, String username) {
        this.twitterService = twitterService;
        this.repository = repository;
        this.username = username;
    }

    @Override
    public void run() {
        String weather = repository.getWeatherString();
        try {
            twitterService.sendTwitterDirectMessage(username,weather);
        } catch (TwitterException e) {
            LOGGER.error(e.getMessage(),e);
        }
    }
}
