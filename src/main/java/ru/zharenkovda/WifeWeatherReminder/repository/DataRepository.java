package ru.zharenkovda.WifeWeatherReminder.repository;

import org.springframework.stereotype.Repository;

@Repository
public class DataRepository {

    private String todayWeatherString;

    private String tommorowWeatherString;

    private String phoneNumber;

    private String twitterUserName;

    private String telegramChatId;

    public String getTodayWeatherString() {
        return todayWeatherString;
    }

    public void setTodayWeatherString(String todayWeatherString) {
        this.todayWeatherString = todayWeatherString;
    }

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

    public String getTelegramChatId() {
        return telegramChatId;
    }

    public void setTelegramChatId(String telegramChatId) {
        this.telegramChatId = telegramChatId;
    }

    public String getTommorowWeatherString() {
        return tommorowWeatherString;
    }

    public void setTommorowWeatherString(String tommorowWeatherString) {
        this.tommorowWeatherString = tommorowWeatherString;
    }
}
