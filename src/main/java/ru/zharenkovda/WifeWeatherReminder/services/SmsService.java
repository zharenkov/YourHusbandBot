package ru.zharenkovda.WifeWeatherReminder.services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    public static final String ACCOUNT_SID = "ACd0d969af67856c208b7fba9095066ad3";
    public static final String AUTH_TOKEN = "6fe63fd89946b1006585d9688c49d479";

    public void sendTwilioSmsForecast(String phoneNumber,String text){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber(phoneNumber), new PhoneNumber("+14159910216"),
                text)
                .create();

        System.out.println(message.getSid());
    }
}
