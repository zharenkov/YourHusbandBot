package ru.zharenkovda.YourHusbandBot.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@PropertySource("classpath:twitter_oauth.properties")
public class SettingsBean {


    private String phoneNumber;

    private String twitterUserName;

    private Set<String> telegramChatIds = new HashSet<>();

    @Value("${oauth.consumer.key}")
    private String twitterConsumerKey;

    @Value("${oauth.consumer.secret}")
    private String twitterConsumerSecret;

    @Value("${oauth.access.key}")
    private String twitterAccessKey;

    @Value("${oauth.access.secret}")
    private String twitterAccessSecret;



    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTwitterUserName() {
        return twitterUserName;
    }

    public void setTwitterUserName(String twitterUserName) {
        this.twitterUserName = twitterUserName;
    }


    public Set<String> getTelegramChatIds() {
        return telegramChatIds;
    }

    public void setTelegramChatIds(Set<String> telegramChatIds) {
        this.telegramChatIds = telegramChatIds;
    }

    public String getTwitterConsumerKey() {
        return twitterConsumerKey;
    }

    public String getTwitterConsumerSecret() {
        return twitterConsumerSecret;
    }

    public String getTwitterAccessKey() {
        return twitterAccessKey;
    }

    public String getTwitterAccessSecret() {
        return twitterAccessSecret;
    }
}
