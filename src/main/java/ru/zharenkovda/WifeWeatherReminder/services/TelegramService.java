package ru.zharenkovda.WifeWeatherReminder.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.zharenkovda.WifeWeatherReminder.utils.PropsUtils;
import ru.zharenkovda.WifeWeatherReminder.utils.RestRequestHelper;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.Properties;


@Service
public class TelegramService {

    private static final String token = "545830164:AAGPd1F0Z-YDeEuB1Iyo0Q7O3k2y32X_BkY";
    private static final String telegramUrl = "https://api.telegram.org/bot";


    public void sendTelegramBotMessage(String text, String chatId) {
        try {
            if (StringUtils.isEmpty(text)){
                System.out.println("Text message is empty");
                return;
            }
            String urlString = String.format("%s%s/sendMessage?chat_id=%s&text=%s",telegramUrl,token,chatId, URLEncoder.encode(text, "UTF-8"));
            String response = RestRequestHelper.sendGetRequest(urlString);
            System.out.println(response);
        } catch (UnsupportedEncodingException e){
            System.out.println("Cannot encode message: " + text);
        }
        catch (IOException e){
            System.out.println("Message sending failed");
        }
    }

    public void sendTelegramBotSticker(String stickerCode, String chatId) {
        String urlString = String.format("%s%s/sendSticker?chat_id=%s&sticker=%s",telegramUrl,token,chatId, stickerCode);
        try {
            String response = RestRequestHelper.sendGetRequest(urlString);
            System.out.println(response);
        } catch (IOException e){
            System.out.println("Message sending failed");
        }
    }


    public Long getUpdateTime() {
        String urlString = String.format("%s%s/getUpdates",telegramUrl,token);
        try {
            String response = RestRequestHelper.sendGetRequest(urlString);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(response.toString());
            if (node.get("result").size()>0) {
                return node.get("result").get(node.get("result").size() - 1).get("message").get("date").longValue();
            } else {
                return 0L;
            }
        } catch (IOException e){
            System.out.println("Unable get updates");
            return 0L;
        }
    }
}
