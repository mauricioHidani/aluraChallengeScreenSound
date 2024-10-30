package br.com.challenge.alura.sound.screen.services;

import br.com.challenge.alura.sound.screen.exceptions.NotFoundException;
import br.com.challenge.alura.sound.screen.models.entities.Artist;
import br.com.challenge.alura.sound.screen.models.menus.MainMenu;
import br.com.challenge.alura.sound.screen.models.menus.MenuColor;
import br.com.challenge.alura.sound.screen.repositories.ArtistRepository;
import br.com.challenge.alura.sound.screen.utils.TerminalColorUtil;
import br.com.challenge.alura.sound.screen.utils.TextUtil;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.CompletionResult;
import com.theokanning.openai.service.OpenAiService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;

@Service
public class ChatGptService {

	private final Scanner scanner;
	private final ArtistRepository artistRepository;
	private final OpenAiService openAiService;

	public ChatGptService(Scanner scanner,
						  ArtistRepository artistRepository,
						  OpenAiService openAiService) {
		this.scanner = scanner;
		this.artistRepository = artistRepository;
		this.openAiService = openAiService;
	}

	@Transactional
	public void searchAboutArtist() {
		System.out.println("\nOpção > " + MainMenu.SEARCH_DATA_ABOUT_ARTIST);

		System.out.print("Informe o nome do artista que deseja saber sobre: ");
		String artistName = scanner.nextLine();

		Optional<Artist> founded = artistRepository.findByNameContainingIgnoreCase(artistName);
		if (!founded.isPresent()) {
			throw new NotFoundException(
				"Não foi encontrado na base de dados da aplicação o artista informado"
			);
		}

		if (founded.get().getDescription() == null || founded.get().getDescription().isBlank()) {
			CompletionRequest request = CompletionRequest.builder()
					.model("gpt-3.5-turbo-instruct")
					.prompt("Escreva sobre o(a) artita %s músical limitando em no máximo 255 caracteres".formatted(founded.get().getName()))
					.maxTokens(1000)
					.temperature(0.7)
					.build();
			CompletionResult response = openAiService.createCompletion(request);
			String resultDescription = response.getChoices().get(0).getText();

			String descriptionNormalized = TextUtil.breakText(resultDescription, 64);
			artistRepository.updateDescription(founded.get().getId(), descriptionNormalized);
			founded.get().setDescription(descriptionNormalized);
		}

		System.out.printf(
			"%s\n %s\n",
			TerminalColorUtil.toColor(MenuColor.PURPLE, "Descrição:"),
			founded.get().getDescription()
		);
	}
}
