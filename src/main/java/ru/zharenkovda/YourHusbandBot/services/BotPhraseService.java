package ru.zharenkovda.YourHusbandBot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zharenkovda.YourHusbandBot.repository.DatabaseDAO;
import ru.zharenkovda.YourHusbandBot.enumerations.BotPhraseType;
import ru.zharenkovda.YourHusbandBot.enumerations.WeatherType;
import ru.zharenkovda.YourHusbandBot.utils.SettingsBean;
import twitter4j.TwitterException;

import java.util.Set;

@Service
public class BotPhraseService {

    @Autowired
    private DatabaseDAO databaseDAO;

    @Autowired
    private TelegramService telegramService;

    @Autowired
    private TrafficService trafficService;

    @Autowired
    private SettingsBean settingsBean;

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private TwitterService twitterService;


    public void saySomethingLovely() {
        Set<String> chatIds = settingsBean.getTelegramChatIds();
        for (String chatId : chatIds) {
            double probability = Math.random();
            if (probability >= 0.6) {
                telegramService.sendTelegramBotMessage(databaseDAO.getRandomPhrasesByType(BotPhraseType.LOVE).getPhrase(), chatId);
            }
            if (probability < 0.6 || probability >= 0.85) {
                telegramService.sendTelegramBotSticker(databaseDAO.getRandomSticker().getStickerCode(), chatId);
            }
        }
    }

    public void saySomethingLovely(String chatId) {
            double probability = Math.random();
            if (probability >= 0.6) {
                telegramService.sendTelegramBotMessage(databaseDAO.getRandomPhrasesByType(BotPhraseType.LOVE).getPhrase(), chatId);
            }
            if (probability < 0.6 || probability >= 0.85) {
                telegramService.sendTelegramBotSticker(databaseDAO.getRandomSticker().getStickerCode(), chatId);
            }
    }

    public void sayGoodMorining(){
        Set<String> chatIds = settingsBean.getTelegramChatIds();
        for (String chatId : chatIds) {
            telegramService.sendTelegramBotMessage(databaseDAO.getRandomPhrasesByType(BotPhraseType.MORNING).getPhrase(), chatId);
        }
    }


    public void sayCommon() {
        Set<String> chatIds = settingsBean.getTelegramChatIds();
        for (String chatId : chatIds) {
            telegramService.sendTelegramBotMessage(databaseDAO.getRandomPhrasesByType(BotPhraseType.COMMON).getPhrase(), chatId);
        }
    }

    public void sayCommon(String chatId) {
        telegramService.sendTelegramBotMessage(databaseDAO.getRandomPhrasesByType(BotPhraseType.COMMON).getPhrase(), chatId);
    }

    public void sayCustomPhrase(String chatId, String text){
        telegramService.sendTelegramBotMessage(text,chatId);
    }

    public void sendWeather(WeatherType weatherType){
        Set<String> chatIds = settingsBean.getTelegramChatIds();
        for (String chatId : chatIds) {
            switch (weatherType){
                case TODAY:
                    telegramService.sendTelegramBotMessage(weatherService.getTodayWeather(),chatId);
                    break;
                case TOMMOROW:
                    telegramService.sendTelegramBotMessage(weatherService.getTommorowWeather(),chatId);
                    break;
            }
        }
    }

    public void sendWeather(String chatId, WeatherType weatherType) {
        switch (weatherType){
            case TODAY:
                telegramService.sendTelegramBotMessage(weatherService.getTodayWeather(),chatId);
                break;
            case TOMMOROW:
                telegramService.sendTelegramBotMessage(weatherService.getTommorowWeather(),chatId);
                break;
        }
    }

    public void sendTraffic() {
        Set<String> chatIds = settingsBean.getTelegramChatIds();
        for (String chatId : chatIds) {
            telegramService.sendTelegramBotMessage(trafficService.getTrafficMessage(),chatId);
        }
    }

    public void sendTraffic(String chatId) {
        telegramService.sendTelegramBotMessage(trafficService.getTrafficMessage(),chatId);
    }

    public void easterEggForTwitter(){
        try {
            twitterService.sendTwitterDirectMessage(settingsBean.getTwitterUserName(),
                    "Привет! Еще я умею писать и в твиттер. Но только чуть-чуть.");
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        ;
    }

}
