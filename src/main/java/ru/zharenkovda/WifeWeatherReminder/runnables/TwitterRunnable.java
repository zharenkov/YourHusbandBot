package ru.zharenkovda.WifeWeatherReminder.runnables;

import org.apache.commons.lang3.StringUtils;
import ru.zharenkovda.WifeWeatherReminder.repository.WeatherRepository;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class TwitterRunnable implements Runnable {
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
                e.printStackTrace();
            }
        }

    }
}
