package ru.zharenkovda.WifeWeatherReminder.runnables;

import ru.zharenkovda.WifeWeatherReminder.repository.WeatherRepository;
import ru.zharenkovda.WifeWeatherReminder.services.WeatherService;

import java.io.IOException;

public class WeatherRunnable implements Runnable {


    private WeatherRepository weatherRepository;
    private WeatherService weatherService;

    public WeatherRunnable(WeatherRepository weatherRepository, WeatherService weatherService) {
        this.weatherRepository = weatherRepository;
        this.weatherService = weatherService;
    }

    @Override
    public void run() {
        try {
           weatherRepository.setWeatherString(weatherService.getWeatherString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
