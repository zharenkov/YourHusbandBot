package ru.zharenkovda.WifeWeatherReminder.runnables;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.zharenkovda.WifeWeatherReminder.repository.DataRepository;
import ru.zharenkovda.WifeWeatherReminder.services.WeatherService;

import java.io.IOException;

@Deprecated
public class WeatherRunnable implements Runnable {
    private static final Logger LOGGER= LoggerFactory.getLogger(WeatherRunnable.class);

    private DataRepository dataRepository;
    private WeatherService weatherService;

    public WeatherRunnable(DataRepository dataRepository, WeatherService weatherService) {
        this.dataRepository = dataRepository;
        this.weatherService = weatherService;
    }

    @Override
    public void run() {
        try {
           dataRepository.setWeatherString(weatherService.getWeatherString());
        } catch (IOException e) {
            LOGGER.error(e.getMessage(),e);
        }
    }
}
