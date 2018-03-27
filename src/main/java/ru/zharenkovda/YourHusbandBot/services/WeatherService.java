package ru.zharenkovda.YourHusbandBot.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.stereotype.Service;
import ru.zharenkovda.YourHusbandBot.utils.RestRequestHelper;

import java.io.IOException;

@Service
public class WeatherService {

    private String darkSkyUrl = "https://api.darksky.net/forecast/cf3f211102b3f5a195c897ff6b52980c/53.2,50.15?units=si&lang=ru";

    public String getTodayWeather() {
        try {
            return parseTodayWeather(getWeatherJSON());
        } catch (IOException e){
            return "";
        }

    }

    public String getTommorowWeather() {
        try {
            return parseTomorrowWeather(getWeatherJSON());
        } catch (IOException e){
            return "";
        }
    }

    private String getWeatherJSON() throws IOException {
        String response = RestRequestHelper.sendGetRequest(darkSkyUrl);
        return response;
    }

    private String parseTodayWeather(String jsonString) throws IOException {
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

    private String parseTomorrowWeather(String jsonString) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(jsonString);

        JsonNode tommorowDayDailyData = node.get("daily").get("data").get(1);
        String tommorowSummary = tommorowDayDailyData.get("summary").asText().toLowerCase();
        Long maxTemp = Math.round(tommorowDayDailyData.get("temperatureHigh").asDouble());
        Long minTemp = Math.round(tommorowDayDailyData.get("temperatureLow").asDouble());
        Long minApparentTemp = Math.round(tommorowDayDailyData.get("apparentTemperatureMin").asDouble());
        Long windGust = Math.round(tommorowDayDailyData.get("windGust").asDouble());
        Long windSpeed = Math.round(tommorowDayDailyData.get("windSpeed").asDouble());
        DateTime sunrise = new DateTime(tommorowDayDailyData.get("sunriseTime").asLong()*1000, DateTimeZone.forID("Europe/Samara"));
        String time = sunrise.getHourOfDay()+":"+sunrise.getMinuteOfHour();

        String result = String.format("Завтра %s " +
                        "Максимальная температура %d℃, минимальная температура %d℃, ощущается как %d℃. Скорость ветра %dм/с с порывами до %dм/с. Восход солнца в %s.",
                tommorowSummary,maxTemp,minTemp,minApparentTemp,windSpeed,windGust,time);

        return result;
    }
}
