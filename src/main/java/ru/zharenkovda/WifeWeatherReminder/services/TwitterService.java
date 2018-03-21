package ru.zharenkovda.WifeWeatherReminder.services;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.zharenkovda.WifeWeatherReminder.utils.PropsUtils;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Properties;

@Service
public class TwitterService {
    private static String twitterAuthPropsFileName = "twitter_oauth.properties";
    private Twitter twitter;

    @PostConstruct
    public void init() throws IOException {
        Properties twitterProps = null;
        twitterProps = PropsUtils.loadProperties(twitterAuthPropsFileName);
        if (twitterProps == null){
            throw new IOException("Props file is empty");
        }

        ConfigurationBuilder cb = new ConfigurationBuilder()
                .setOAuthConsumerKey(twitterProps.getProperty("oauth.consumer.key"))
                .setOAuthConsumerSecret(twitterProps.getProperty("oauth.consumer.secret"))
                .setOAuthAccessToken(twitterProps.getProperty("oauth.access.key"))
                .setOAuthAccessTokenSecret(twitterProps.getProperty("oauth.access.secret"));

        this.twitter = new TwitterFactory(cb.build()).getInstance();

    }

    public void sendTwitterDirectMessage(String username,String message) throws TwitterException {
        if (StringUtils.isNotEmpty(message)){
                twitter.directMessages().sendDirectMessage(username,message);
        }
    }
}
