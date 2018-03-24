package ru.zharenkovda.WifeWeatherReminder.utils;

import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class SettingsBean {


    private String phoneNumber;

    private String twitterUserName;

    private Set<String> telegramChatIds = new HashSet<>();


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
}
