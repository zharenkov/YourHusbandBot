package ru.zharenkovda.WifeWeatherReminder.runnables;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.zharenkovda.WifeWeatherReminder.repository.WeatherRepository;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class TwitterRunnable implements Runnable {
    private static final Logger LOGGER= LoggerFactory.getLogger(TwitterRunnable.class);


    private Twitter twitter;
    private WeatherRepository repository;
    private String username;

    public TwitterRunnable(Twitter twitter, WeatherRepository repository, String username) {
        this.twitter = twitter;
        this.repository = repository;
        this.username = username;
    }

    @Override
    public void run() {
        String weather = repository.getWeatherString();
        if (StringUtils.isNotEmpty(weather)){
            try {
                twitter.directMessages().sendDirectMessage(username,weather);
            } catch (TwitterException e) {
                LOGGER.error(e.getMessage(),e);
            }
        } else {
            LOGGER.warn("No weather info in repository");
        }

    }
}
