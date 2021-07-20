package com.webapp.config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.webapp.model.User;
import com.webapp.repository.UserRepository;

@Configuration
public class UserConfig {
	
	@Bean
	CommandLineRunner commandLineRunner(UserRepository repository) {
		return args -> {
			User karina = new User(
					"Karina",
					"Oguido",
					"karina.oguido@gmail.com"
					);

			User ana = new User(
					"Ana",
					"Silva",
					"ana.silva@gmail.com"
					);

			User pedro = new User(
					"Pedro",
					"Souza",
					"pedro.souza@gmail.com"
					);
			
			repository.saveAll(List.of(karina, ana, pedro));
		};
	}
}
