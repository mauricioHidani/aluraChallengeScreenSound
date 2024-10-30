package br.com.challenge.alura.sound.screen.services;

import br.com.challenge.alura.sound.screen.models.entities.Artist;
import br.com.challenge.alura.sound.screen.models.enums.ArtistType;
import br.com.challenge.alura.sound.screen.models.menus.MainMenu;
import br.com.challenge.alura.sound.screen.models.menus.MenuColor;
import br.com.challenge.alura.sound.screen.repositories.ArtistRepository;
import br.com.challenge.alura.sound.screen.utils.QuestionUtil;
import br.com.challenge.alura.sound.screen.utils.TerminalColorUtil;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class ArtistService {

	private final Scanner scanner;
	private final ArtistRepository artistRepository;

	public ArtistService(Scanner scanner, ArtistRepository artistRepository) {
		this.scanner = scanner;
		this.artistRepository = artistRepository;
	}

	public void register() {
		boolean isNewRegister = true;
		do {
			System.out.println("\nOpção > " + MainMenu.REGISTER_ARTIST);

			System.out.print("Informe o nome do Artista: ");
			String artistName = scanner.nextLine();

			System.out.print("Informe o tipo desse Artista (Solo, Dupla ou Banda): ");
			String artistType = scanner.nextLine();

			if (!ArtistType.contains(artistType)) {
				throw new IllegalArgumentException("Tipo de artista informado é inválido");
			}

			Artist artist = new Artist(artistName, ArtistType.convertFrom(artistType));
			artistRepository.save(artist);

			System.out.println(
				TerminalColorUtil.toColor(MenuColor.GREEN, "\nArtista salvo na base de dados")
			);
			System.out.println(TerminalColorUtil.toColor(MenuColor.GRAY, artist));

			System.out.print("\nDeseja cadastrar mais um artista? (S/n) ");
			String choice = scanner.nextLine();
			isNewRegister = QuestionUtil.getChoice(choice);

		} while (isNewRegister);
	}
}
