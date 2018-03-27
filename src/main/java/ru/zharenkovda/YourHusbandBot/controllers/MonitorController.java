package ru.zharenkovda.YourHusbandBot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.zharenkovda.YourHusbandBot.utils.SettingsBean;

import java.util.stream.Collectors;

@RestController
@EnableAutoConfiguration
@RequestMapping("/")
public class MonitorController {

    @Autowired
    private SettingsBean settingsBean;


    @RequestMapping("/chatIds")
    public String getChatId() {
        return settingsBean.getTelegramChatIds().stream().collect(Collectors.joining(", "));
    }

    @RequestMapping("/username")
    public String getTwitterUserName() {
        return settingsBean.getTwitterUserName();
    }

    @RequestMapping("/stop-all")
    public void stopApplication(){
        System.exit(0);
    }


}
