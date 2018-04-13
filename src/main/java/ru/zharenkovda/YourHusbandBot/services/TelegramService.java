package ru.zharenkovda.YourHusbandBot.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zharenkovda.YourHusbandBot.utils.SettingsBean;
import ru.zharenkovda.YourHusbandBot.utils.RestRequestHelper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


@Service
public class TelegramService {

    private static final String token = "545830164:AAGPd1F0Z-YDeEuB1Iyo0Q7O3k2y32X_BkY";
    private static final String telegramUrl = "https://api.telegram.org/bot";

    @Autowired
    private SettingsBean settingsBean;

    public void sendTelegramBotMessage(String text, String chatId) {
        try {
            if (StringUtils.isEmpty(text)) {
                System.out.println("Text message is empty");
                return;
            }
            String urlString = String.format("%s%s/sendMessage?chat_id=%s&text=%s", telegramUrl, token, chatId, URLEncoder.encode(text, "UTF-8"));
            String response = RestRequestHelper.sendGetRequest(urlString);
            System.out.println(response);
        } catch (UnsupportedEncodingException e) {
            System.out.println("Cannot encode message: " + text);
        } catch (IOException e) {
            System.out.println("Message sending failed");
        }
    }

    public void sendTelegramBotSticker(String stickerCode, String chatId) {
        String urlString = String.format("%s%s/sendSticker?chat_id=%s&sticker=%s", telegramUrl, token, chatId, stickerCode);
        try {
            String response = RestRequestHelper.sendGetRequest(urlString);
            System.out.println(response);
        } catch (IOException e) {
            System.out.println("Message sending failed");
        }
    }

    /**
     * messages and chats to response
     * @return key - chatId, messageToResponse - is response needed
     */
    public Map<String, String> getJsonUpdates() {
        Map<String, String> result = new HashMap<>();
        String urlString = String.format("%s%s/getUpdates", telegramUrl, token);
        try {
            String response = RestRequestHelper.sendGetRequest(urlString);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(response.toString());
            if (node.get("result").size() > 0) {
                node.get("result").forEach(resultNode -> {
                    String chatId = resultNode.get("message").get("chat").get("id").asText();
                    Long date = resultNode.get("message").get("date").longValue();
                    String message = "sticker";
                    // message->text is null if user send sticker :(
                    if (resultNode.get("message").get("text") != null) {
                        message = resultNode.get("message").get("text").asText();
                    }
                    settingsBean.getTelegramChatIds().add(chatId);
                    if(isMessageNeedResp(date)) {
                        result.put(chatId, message);
                    }
                });
            }
            //TODO logger
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private boolean isMessageNeedResp(Long date){
        Long currentDate =System.currentTimeMillis()/1000;
        if (currentDate-date<16){
            return true;
        }
        return false;
    }
}
