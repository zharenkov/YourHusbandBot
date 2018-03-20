package ru.zharenkovda.WifeWeatherReminder.repository;

import org.springframework.stereotype.Repository;

@Repository
public class WeatherRepository {

    private String weatherString;

    public String getWeatherString() {
        return weatherString;
    }

    public void setWeatherString(String weatherString) {
        this.weatherString = weatherString;
    }
}
