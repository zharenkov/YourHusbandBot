package ru.zharenkovda.WifeWeatherReminder.runnables;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.zharenkovda.WifeWeatherReminder.repository.WeatherRepository;
import ru.zharenkovda.WifeWeatherReminder.services.WeatherService;

import java.io.IOException;

public class WeatherRunnable implements Runnable {
    private static final Logger LOGGER= LoggerFactory.getLogger(WeatherRunnable.class);

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
            LOGGER.error(e.getMessage(),e);
        }
    }
}
