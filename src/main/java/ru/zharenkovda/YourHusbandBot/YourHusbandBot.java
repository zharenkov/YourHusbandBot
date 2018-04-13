package ru.zharenkovda.YourHusbandBot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.zharenkovda.YourHusbandBot.services.BotPhraseService;
import ru.zharenkovda.YourHusbandBot.utils.SettingsBean;

@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories
public class YourHusbandBot implements ApplicationRunner {

    @Autowired
    private SettingsBean settingsBean;

    @Autowired
	private BotPhraseService botPhraseService;

    private String welcomePhrase = "Привет! Я бот версии 2.1. Выяснилось, что мой механизм ответов сломался из-за отправки мне стикеров. Пришлось мимикрировать. Теперь я стал еще лучше!";

    public static void main(String[] args) {
        SpringApplication.run(YourHusbandBot.class, args);

    }

	//126264498 or 266960149 zharenkov or Teacholic +79270071991
	@Override
	public void run(ApplicationArguments args) throws Exception {
		if (args.getSourceArgs().length>0) {
			settingsBean.getTelegramChatIds().add(args.getSourceArgs()[0]);
			botPhraseService.sayCustomPhrase(args.getSourceArgs()[0],welcomePhrase);
			if (args.getSourceArgs().length>1) {
				settingsBean.setTwitterUserName(args.getSourceArgs()[1]);
				if (args.getSourceArgs().length > 2) {
					settingsBean.setPhoneNumber(args.getSourceArgs()[2]);
				}
			}
		}
	}
}
