package br.com.challenge.alura.sound.screen;

import br.com.challenge.alura.sound.screen.main.MainApp;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenSoundApplication implements CommandLineRunner {

	private final MainApp main;

	public ScreenSoundApplication(MainApp main) {
		this.main = main;
	}

	public static void main(String[] args) {
		SpringApplication.run(ScreenSoundApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		main.show();
	}
}