package br.com.challenge.alura.sound.screen.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
public class ApplicationConfig {

	@Bean
	public Scanner scanner() {
		return new Scanner(System.in);
	}
}
