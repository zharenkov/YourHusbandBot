package ru.zharenkovda.WifeWeatherReminder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class WifeWeatherReminder {

	public static void main(String[] args) {
		SpringApplication.run(WifeWeatherReminder.class, args);
	}
}
