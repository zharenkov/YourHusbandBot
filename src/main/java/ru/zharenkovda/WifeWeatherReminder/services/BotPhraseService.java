package ru.zharenkovda.WifeWeatherReminder.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zharenkovda.WifeWeatherReminder.dao.DatabaseDAO;
import ru.zharenkovda.WifeWeatherReminder.enumerations.BotPhraseType;
import ru.zharenkovda.WifeWeatherReminder.enumerations.WeatherType;
import ru.zharenkovda.WifeWeatherReminder.repository.DataRepository;

@Service
public class BotPhraseService {

    @Autowired
    DatabaseDAO databaseDAO;

    @Autowired
    TelegramService telegramService;

    @Autowired
    DataRepository dataRepository;


    public void saySomethingLovely() {
        String chatId = dataRepository.getTelegramChatId();
        double probability = Math.random();
        if (probability>=0.65) {
            telegramService.sendTelegramBotMessage(databaseDAO.getRandomPhrasesByType(BotPhraseType.LOVE).getPhrase(), chatId);
        }
        if (probability<0.65 || probability>=0.9){
            telegramService.sendTelegramBotSticker(databaseDAO.getRandomSticker().getStickerCode(),chatId);
        }
    }

    public void sayGoodMorining(){
        String chatId = dataRepository.getTelegramChatId();
        telegramService.sendTelegramBotMessage(databaseDAO.getRandomPhrasesByType(BotPhraseType.MORNING).getPhrase(),chatId);
    }


    public void sayCommon(){
        String chatId = dataRepository.getTelegramChatId();
        telegramService.sendTelegramBotMessage(databaseDAO.getRandomPhrasesByType(BotPhraseType.COMMON).getPhrase(),chatId);
    }

    public void sendWeather(WeatherType weatherType){
        String chatId = dataRepository.getTelegramChatId();
        switch (weatherType){
            case TODAY:
                telegramService.sendTelegramBotMessage(dataRepository.getTodayWeatherString(),chatId);
                break;
            case TOMMOROW:
                telegramService.sendTelegramBotMessage(dataRepository.getTommorowWeatherString(),chatId);
                break;
        }
    }


}
