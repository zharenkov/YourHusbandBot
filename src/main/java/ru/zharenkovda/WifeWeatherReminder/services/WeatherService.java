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

    public String getWeatherString() throws IOException {
        String json = getWeatherJSON();
        return parseJsonWeatherStringToText(json);
    }

    private String getWeatherJSON() throws IOException {
        StringBuilder jsonBuilder = new StringBuilder();
        URL url = new URL("http://api.wunderground.com/api/8b0b570b1abd84a7/forecast/lang:RU/q/Russia/Samara.json");
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String output;
        while ((output = br.readLine())!=null){
            jsonBuilder.append(output);
        }
        return jsonBuilder.toString();
    }

    private String parseJsonWeatherStringToText(String jsonString) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(jsonString);
        JsonNode simpleForecastToday = json.get("forecast").get("simpleforecast").get("forecastday").get(0);
        JsonNode txtForecastToday = json.get("forecast").get("txt_forecast").get("forecastday").get(1);

        if (simpleForecastToday ==null){
            return "";
        }
        String weekday = simpleForecastToday.get("date").get("weekday_short").asText();
        String maxTemp = simpleForecastToday.get("high").get("celsius").asText();
        String minTemp = simpleForecastToday.get("low").get("celsius").asText();
        String windDir = simpleForecastToday.get("avewind").get("dir").asText();
        Long windSpeed = Math.round(simpleForecastToday.get("avewind").get("kph").asDouble()*1000/3600);
        String result = String.format("Сегодня (%s) максимальная температура %s ℃, минимальная температура %s℃. Ветер %s %d м/c. ",
                weekday,maxTemp,minTemp,windDir,windSpeed);
        //bug with fcttext_metric in weather.com service
        if (txtForecastToday !=null) {
            String nightInfoTitle = txtForecastToday.get("title").asText();
            String nightInfo = txtForecastToday.get("fcttext_metric").asText();
            if (StringUtils.isNotEmpty(nightInfoTitle) && StringUtils.isNotEmpty(nightInfo)) {
                char[] nightInfoArr = nightInfo.toCharArray();
                nightInfoArr[0] = Character.toLowerCase(nightInfoArr[0]);
                nightInfo = new String(nightInfoArr).replace("переменчевый","переменчивый");
                result = result + String.format(" %s %s", nightInfoTitle, nightInfo);

            }
        }
        return result;
    }
}
