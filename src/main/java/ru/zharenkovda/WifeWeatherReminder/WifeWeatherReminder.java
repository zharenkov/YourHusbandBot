package ru.zharenkovda.WifeWeatherReminder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.zharenkovda.WifeWeatherReminder.repository.DataRepository;

@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories
public class WifeWeatherReminder implements ApplicationRunner {

	@Autowired
	DataRepository dataRepository;

	public static void main(String[] args) {
		SpringApplication.run(WifeWeatherReminder.class, args);

	}

	//126264498 zharenkov +79270071991
	@Override
	public void run(ApplicationArguments args) throws Exception {
		if (args.getSourceArgs().length>0) {
			dataRepository.setTelegramChatId(args.getSourceArgs()[0]);
			if (args.getSourceArgs().length>1) {
				dataRepository.setTwitterUserName(args.getSourceArgs()[1]);
				if (args.getSourceArgs().length > 2) {
					dataRepository.setPhoneNumber(args.getSourceArgs()[2]);
				}
			}
		}
	}
}
