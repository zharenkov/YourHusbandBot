package ru.zharenkovda.YourHusbandBot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zharenkovda.YourHusbandBot.dto.BotPhraseEntity;
import ru.zharenkovda.YourHusbandBot.dto.StickerEntity;
import ru.zharenkovda.YourHusbandBot.repository.DatabaseDAO;
import ru.zharenkovda.YourHusbandBot.enumerations.BotPhraseType;
import ru.zharenkovda.YourHusbandBot.enumerations.WeatherType;
import ru.zharenkovda.YourHusbandBot.utils.SettingsBean;
import twitter4j.TwitterException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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

    private Map<String,Set<Long>> usedPhrases = new HashMap<>();
    private Map<String,Set<Long>> usedStickers = new HashMap<>();

    public void saySomethingLovely() {
        Set<String> chatIds = settingsBean.getTelegramChatIds();
        for (String chatId : chatIds) {
            saySomethingLovely(chatId);
        }
    }

    public void saySomethingLovely(String chatId) {
            double probability = Math.random();
            if (probability >= 0.6) {
                telegramService.sendTelegramBotMessage(getUnusedPhase(chatId,BotPhraseType.LOVE), chatId);
            }
            if (probability < 0.6 || probability >= 0.85) {
                telegramService.sendTelegramBotSticker(getUnusedSticker(chatId), chatId);
            }
    }


    public void sayGoodMorining(){
        Set<String> chatIds = settingsBean.getTelegramChatIds();
        for (String chatId : chatIds) {
            telegramService.sendTelegramBotMessage(getUnusedPhase(chatId,BotPhraseType.MORNING), chatId);
        }
    }


    public void sayCommon() {
        Set<String> chatIds = settingsBean.getTelegramChatIds();
        for (String chatId : chatIds) {
            sayCommon(chatId);
        }
    }

    public void sayCommon(String chatId) {
        telegramService.sendTelegramBotMessage(getUnusedPhase(chatId,BotPhraseType.COMMON), chatId);
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

    private String getUnusedPhase(String chatId, BotPhraseType type){
        BotPhraseEntity phrase = null;
        if (usedPhrases.get(chatId) == null){
            usedPhrases.put(chatId,new HashSet<>());
        }
        do {
            phrase = databaseDAO.getRandomPhrasesByType(type);
        } while (usedPhrases.get(chatId).contains(phrase.getId()));
        Set<Long> usedSet = usedPhrases.get(chatId);
        usedSet.add(phrase.getId());
        usedPhrases.put(chatId,usedSet);
        return phrase.getPhrase();
    }

    private String getUnusedSticker(String chatId){
        StickerEntity sticker = null;
        if (usedStickers.get(chatId) == null){
            usedStickers.put(chatId,new HashSet<>());
        }
        do {
            sticker = databaseDAO.getRandomSticker();
        } while (usedStickers.get(chatId).contains(sticker.getId()));
        Set<Long> usedSet = usedStickers.get(chatId);
        usedSet.add(sticker.getId());
        usedStickers.put(chatId,usedSet);
        return sticker.getStickerCode();
    }

    public void clearPhrasesAndStickers(){
        usedStickers = new HashMap<>();
        usedPhrases  = new HashMap<>();
    }
}
