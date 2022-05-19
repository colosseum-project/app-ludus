package org.colosseumproject.ludus;

import org.colosseumproject.ludus.configuration.ArenaConfiguration;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class LudusApplication {

	private static org.slf4j.Logger log = LoggerFactory.getLogger(LudusApplication.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(LudusApplication.class, args);
		ArenaConfiguration arenaConfiguration = context.getBean(ArenaConfiguration.class);
		log.info("Arena endpoint set to '" + arenaConfiguration.getEndpoint() + "'");
	}

}
