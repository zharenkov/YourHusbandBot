package ru.zharenkovda.YourHusbandBot.services;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zharenkovda.YourHusbandBot.utils.SettingsBean;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Service
public class TwitterService {
    private Twitter twitter;

    @Autowired
    private SettingsBean settingsBean;

    @PostConstruct
    public void init() throws IOException {
        ConfigurationBuilder cb = new ConfigurationBuilder()
                .setOAuthConsumerKey(settingsBean.getTwitterConsumerKey())
                .setOAuthConsumerSecret(settingsBean.getTwitterConsumerSecret())
                .setOAuthAccessToken(settingsBean.getTwitterAccessKey())
                .setOAuthAccessTokenSecret(settingsBean.getTwitterAccessSecret());

        this.twitter = new TwitterFactory(cb.build()).getInstance();

    }

    public void sendTwitterDirectMessage(String username,String message) throws TwitterException {
        if (StringUtils.isNotEmpty(message)){
                twitter.directMessages().sendDirectMessage(username,message);
        }
    }
}
