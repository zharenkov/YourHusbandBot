package ru.zharenkovda.WifeWeatherReminder.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.zharenkovda.WifeWeatherReminder.repository.WeatherRepository;
import ru.zharenkovda.WifeWeatherReminder.runnables.WeatherRunnable;
import ru.zharenkovda.WifeWeatherReminder.services.SchedulerService;
import ru.zharenkovda.WifeWeatherReminder.services.WeatherService;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@RestController
@EnableAutoConfiguration
@RequestMapping("/weather")
public class WeatherPollingController {


    @Autowired
    private WeatherRepository weatherRepository;

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private SchedulerService schedulerService;

    @RequestMapping("/")
    public String getWeather() throws IOException {
        return weatherRepository.getWeatherString();

    }

    @RequestMapping("/start")
    public void startWeatherPolling() throws IOException, ExecutionException, InterruptedException {
        schedulerService.executeRunnableTask(new WeatherRunnable(weatherRepository,weatherService),4L, TimeUnit.HOURS);
    }

}
