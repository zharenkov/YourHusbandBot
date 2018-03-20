package ru.zharenkovda.WifeWeatherReminder.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.io.IOException;
import java.util.Properties;

public class TwitterFactoryImpl {

    public static Twitter getIntsance(String twitterAuthPropsFileName) throws IOException {
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

        return new TwitterFactory(cb.build()).getInstance();
    }
}
