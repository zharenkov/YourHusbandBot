package ru.zharenkovda.WifeWeatherReminder.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.zharenkovda.WifeWeatherReminder.repository.DataRepository;

@RestController
@EnableAutoConfiguration
@RequestMapping("/")
public class MonitorController {

    @Autowired
    DataRepository dataRepository;

    @RequestMapping("/weather")
    public String getWeather() {
        return dataRepository.getTodayWeatherString();
    }

    @RequestMapping("/chatId")
    public String getChatId() {
        return dataRepository.getTelegramChatId();
    }

    @RequestMapping("/username")
    public String getTwitterUserName() {
        return dataRepository.getTwitterUserName();
    }

    @RequestMapping("/stop-all")
    public void stopApplication(){
        System.exit(0);
    }


}
