package ru.zharenkovda.WifeWeatherReminder.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropsUtils {

    public static Properties loadProperties(String propsFileName) throws IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        FileInputStream fis = new FileInputStream(classLoader.getResource(propsFileName).getFile());
        Properties properties = new Properties();
        properties.load(fis);
        return properties;
    }
}
