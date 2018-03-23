package ru.zharenkovda.WifeWeatherReminder.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.zharenkovda.WifeWeatherReminder.repository.DataRepository;
import ru.zharenkovda.WifeWeatherReminder.services.WeatherService;

import java.io.IOException;

@Component
public class WeatherScheduler {

    @Autowired
    WeatherService weatherService;

    @Autowired
    DataRepository dataRepository;

    @Scheduled(cron = "0/30 * * * * 1-6", zone = "Europe/Samara")
    public void getTommorowWeather() {
        try {
            System.out.println("Weather getting");
            dataRepository.setTommorowWeatherString(weatherService.getTommorowWeather());
        } catch (IOException e) {
            System.out.println("Unable get weather");
        }
    }

    @Scheduled(cron = "0/30 * * * * 1-6", zone = "Europe/Samara")
    public void getTodayWeather() {
        try {
            System.out.println("Weather getting");
            dataRepository.setTodayWeatherString(weatherService.getTodayWeather());
        } catch (IOException e) {
            System.out.println("Unable get weather");
        }
    }
}
