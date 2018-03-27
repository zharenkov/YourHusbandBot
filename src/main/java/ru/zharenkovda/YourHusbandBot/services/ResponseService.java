package ru.zharenkovda.YourHusbandBot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zharenkovda.YourHusbandBot.enumerations.WeatherType;
import ru.zharenkovda.YourHusbandBot.utils.SettingsBean;

import java.util.Map;

@Service
public class ResponseService {

    @Autowired
    private TelegramService telegramService;

    @Autowired
    private BotPhraseService botPhraseService;

    @Autowired
    SettingsBean settingsBean;

    public void idleOrResponse(){
        Map<String, String> chats = telegramService.getJsonUpdates();
        for (Map.Entry<String, String> entry : chats.entrySet()){
            String message = entry.getValue().toLowerCase();
            String chatId = entry.getKey();
            if (message.contains("пробк")) {
                botPhraseService.sendTraffic(chatId);
                return;
            }
            if (message.contains("погода")) {
                if (message.contains("завтра")){
                    botPhraseService.sendWeather(chatId, WeatherType.TOMMOROW);
                    return;
                }
                botPhraseService.sendWeather(chatId,WeatherType.TODAY);
                return;
            }
            if (message.contains("твит") || message.contains("twit") ){
                botPhraseService.easterEggForTwitter();
                botPhraseService.sayCustomPhrase(chatId,"Загляни в твиттер");
                return;
            }
            if (message.contains("люб")) {
                botPhraseService.saySomethingLovely(entry.getKey());
                return;
            }
            botPhraseService.sayCommon(chatId);
        }
    }
}
