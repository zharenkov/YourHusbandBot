package ru.zharenkovda.WifeWeatherReminder.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;
import ru.zharenkovda.WifeWeatherReminder.utils.PropsUtils;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Properties;


@Service
public class TelegramService {

    private static final String token = "545830164:AAGPd1F0Z-YDeEuB1Iyo0Q7O3k2y32X_BkY";
    private static final String telegramUrl = "https://api.telegram.org/bot";


    public void sendTelegramBotMessage(String text, String chatId) throws IOException {
        StringBuilder response = new StringBuilder();
        String urlString = String.format("%s%s/sendMessage?chat_id=%s&text=%s",telegramUrl,token,chatId, URLEncoder.encode(text, "UTF-8"));
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String output;
        while ((output = br.readLine()) != null) {
            response.append(output);
        }
        System.out.println(response);
    }
    public Long getUpdateTime() throws IOException {
        StringBuilder response = new StringBuilder();
        String urlString = String.format("%s%s/getUpdates",telegramUrl,token);
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String output;
        while ((output = br.readLine()) != null) {
            response.append(output);
        }
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(response.toString());
        return node.get("result").get(node.get("result").size()-1).get("message").get("date").longValue();
    }
}
