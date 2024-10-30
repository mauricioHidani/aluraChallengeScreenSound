package br.com.challenge.alura.sound.screen.main;

import br.com.challenge.alura.sound.screen.exceptions.NotFoundException;
import br.com.challenge.alura.sound.screen.models.menus.MainMenu;
import br.com.challenge.alura.sound.screen.models.menus.MenuColor;
import br.com.challenge.alura.sound.screen.services.ArtistService;
import br.com.challenge.alura.sound.screen.services.ChatGptService;
import br.com.challenge.alura.sound.screen.services.SongService;
import br.com.challenge.alura.sound.screen.utils.TerminalColorUtil;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class MainApp {

	private final Scanner scanner;
	private final ArtistService artistService;
	private final SongService songService;
	private final ChatGptService chatGptService;

	private final String FINISH_MSG = "Obrigado por usar a Screen Sound para cadastrar e saber mais sobre seus artistas favoritos";

	public MainApp(Scanner scanner,
				   ArtistService artistService,
				   SongService songService,
				   ChatGptService chatGptService) {
		this.scanner = scanner;
		this.artistService = artistService;
		this.songService = songService;
		this.chatGptService = chatGptService;
	}

	public void show() {
		MainMenu optionState = MainMenu.DEFAULT;
		do {
			System.out.println("\n*** SCREEN SONG ***");
			MainMenu.showMenu(MenuColor.PURPLE, MenuColor.GRAY);
			optionState = getChoice();

			try {
				switch (optionState) {
					case REGISTER_ARTIST -> artistService.register();
					case REGISTER_SONG -> songService.register();
					case LIST_SONGS -> songService.findAll();
					case SEARCH_SONGS_BY_ARTIST -> songService.findByArtist();
					case SEARCH_DATA_ABOUT_ARTIST -> chatGptService.searchAboutArtist();
					case EXIST -> {
						System.out.println(FINISH_MSG + "\n");
						System.exit(0);
					}
					default -> optionState = MainMenu.EXIST;
				}

			} catch (IllegalArgumentException | NotFoundException e) {
				System.out.println(
						TerminalColorUtil.toColor(MenuColor.RED, e.getMessage())
				);
			}

		} while	(optionState != MainMenu.EXIST);
	}

	private MainMenu getChoice() {
		System.out.print("Número da opção: ");
		int choice = scanner.nextInt();
		scanner.nextLine();

		MainMenu[] options = MainMenu.values();
		if (choice <= 0 && choice > options.length) {
			throw new IllegalArgumentException("Opção inválida");
		}

		return options[choice];
	}
}
