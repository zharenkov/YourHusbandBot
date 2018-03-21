package ru.zharenkovda.WifeWeatherReminder.services;

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
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Properties;


@Service
public class NotificationService {

    public static final String ACCOUNT_SID = "ACd0d969af67856c208b7fba9095066ad3";
    public static final String AUTH_TOKEN = "6fe63fd89946b1006585d9688c49d479";
    private static final String token = "545830164:AAGPd1F0Z-YDeEuB1Iyo0Q7O3k2y32X_BkY";
    private static final String telegramUrl = "https://api.telegram.org/bot";


    public void sendTwilioSmsForecast(String text){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber("+79270071991"), new PhoneNumber("+14159910216"),
                        text)
                .create();

        System.out.println(message.getSid());
    }


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

    public void sendTwitterDirectMessage() throws IOException {

    }
}
