package ru.zharenkovda.WifeWeatherReminder.utils;

import ru.zharenkovda.WifeWeatherReminder.exceptions.TwitterInitializationException;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.io.IOException;
import java.util.Properties;

public class TwitterFactoryImpl {

    public static Twitter getIntsance(String twitterAuthPropsFileName) throws TwitterInitializationException {
        Properties twitterProps = null;
        try {
            twitterProps = PropsUtils.loadProperties(twitterAuthPropsFileName);
        } catch (IOException e){
            e.printStackTrace();
            throw new TwitterInitializationException("Can't read props file", e);
        }
        if (twitterProps == null){
            throw new TwitterInitializationException( "Props file is empty");
        }

        ConfigurationBuilder cb = new ConfigurationBuilder()
                .setOAuthConsumerKey(twitterProps.getProperty("oauth.consumer.key"))
                .setOAuthConsumerSecret(twitterProps.getProperty("oauth.consumer.secret"))
                .setOAuthAccessToken(twitterProps.getProperty("oauth.access.key"))
                .setOAuthAccessTokenSecret(twitterProps.getProperty("oauth.access.secret"));

        return new TwitterFactory(cb.build()).getInstance();
    }
}
