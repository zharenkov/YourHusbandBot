package ru.zharenkovda.YourHusbandBot.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RestRequestHelper {


    public static String sendGetRequest(String requestUrl) throws IOException {
        StringBuilder response = new StringBuilder();
        URL url = new URL(requestUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String output;
        while ((output = br.readLine()) != null) {
            response.append(output);
        }
        return response.toString();
    }
}
