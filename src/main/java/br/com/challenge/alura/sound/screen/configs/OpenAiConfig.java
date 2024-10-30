package br.com.challenge.alura.sound.screen.configs;

import br.com.challenge.alura.sound.screen.configs.env.OpenAiEnv;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAiConfig {

	private final OpenAiEnv env;

	public OpenAiConfig(OpenAiEnv env) {
		this.env = env;
	}

	@Bean
	public OpenAiService openAiService() {
		return new OpenAiService(env.getApiKey());
	}
}
