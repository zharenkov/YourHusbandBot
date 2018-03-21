package ru.zharenkovda.WifeWeatherReminder.runnables;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.zharenkovda.WifeWeatherReminder.repository.DataRepository;
import ru.zharenkovda.WifeWeatherReminder.services.TwitterService;
import twitter4j.TwitterException;

@Deprecated
public class TwitterRunnable implements Runnable {
    private static final Logger LOGGER= LoggerFactory.getLogger(TwitterRunnable.class);

    private TwitterService twitterService;
    private DataRepository dataRepository;
    private String username;

    public TwitterRunnable(TwitterService twitterService, DataRepository dataRepository, String username) {
        this.twitterService = twitterService;
        this.dataRepository = dataRepository;
        this.username = username;
    }

    @Override
    public void run() {
        String weather = dataRepository.getWeatherString();
        try {
            twitterService.sendTwitterDirectMessage(username,weather);
        } catch (TwitterException e) {
            LOGGER.error(e.getMessage(),e);
        }
    }
}
