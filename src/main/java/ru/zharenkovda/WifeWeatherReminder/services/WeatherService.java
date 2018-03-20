package ru.zharenkovda.WifeWeatherReminder.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class WeatherService {

    private String darkSkyUrl = "https://api.darksky.net/forecast/cf3f211102b3f5a195c897ff6b52980c/53.2,50.15?units=si&lang=ru";
    public String getWeatherString() throws IOException {
        String json = getWeatherJSON();
        return parseJsonDarkSkyStringToText(json);
    }

    private String getWeatherJSON() throws IOException {
        StringBuilder jsonBuilder = new StringBuilder();
        URL url = new URL(darkSkyUrl);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String output;
        while ((output = br.readLine())!=null){
            jsonBuilder.append(output);
        }
        return jsonBuilder.toString();
    }

    private String parseJsonDarkSkyStringToText(String jsonString) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(jsonString);
        JsonNode currentData = node.get("currently");
        String todaysSummaryData = node.get("hourly").get("summary").asText();

        JsonNode todaysDailyData = node.get("daily").get("data").get(0);

        Long currentTemp = Math.round(currentData.get("temperature").asDouble());
        String currentState = currentData.get("summary").asText().toLowerCase();
        Long currentApparentTemp = Math.round(currentData.get("apparentTemperature").asDouble());
        Long maxTemp = Math.round(todaysDailyData.get("temperatureHigh").asDouble());
        Long minTemp = Math.round(todaysDailyData.get("temperatureLow").asDouble());

        Long windGust = Math.round(todaysDailyData.get("windGust").asDouble());
        Long windSpeed = Math.round(todaysDailyData.get("windSpeed").asDouble());
        String result = String.format("Сегодня %s, температура воздуха %d℃, ощущается как %d℃. " +
                "Максимальная температура %d℃, минимальная температура %d℃. Скорость ветра %dм/с с порывами до %dм/с. %s",
                currentState,currentTemp,currentApparentTemp,maxTemp,minTemp,windSpeed,windGust,todaysSummaryData);

        return result;
    }
}
