package br.com.challenge.alura.sound.screen.services;

import br.com.challenge.alura.sound.screen.exceptions.NotFoundException;
import br.com.challenge.alura.sound.screen.models.entities.Artist;
import br.com.challenge.alura.sound.screen.models.entities.Song;
import br.com.challenge.alura.sound.screen.models.menus.MainMenu;
import br.com.challenge.alura.sound.screen.models.menus.MenuColor;
import br.com.challenge.alura.sound.screen.repositories.ArtistRepository;
import br.com.challenge.alura.sound.screen.repositories.SongRepository;
import br.com.challenge.alura.sound.screen.utils.QuestionUtil;
import br.com.challenge.alura.sound.screen.utils.TerminalColorUtil;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class SongService {

	private final Scanner scanner;
	private final SongRepository songRepository;
	private final ArtistRepository artistRepository;

	public SongService(Scanner scanner,
					   SongRepository songRepository,
					   ArtistRepository artistRepository) {
		this.scanner = scanner;
		this.songRepository = songRepository;
		this.artistRepository = artistRepository;
	}

	@Transactional
	public void register() {
		Optional<Artist> artistFounded = Optional.empty();
		boolean	isNewRegister = true;
		do {
			System.out.println("\nOpção > " + MainMenu.REGISTER_SONG);

			if (artistFounded.isEmpty()) {
				System.out.print("Informe o Artista da música: ");
				String artistName = scanner.nextLine();
				artistFounded = artistRepository.findByNameContainingIgnoreCase(artistName);

				if (!artistFounded.isPresent()) {
					throw new NotFoundException("Não foi encontrado o Artista informado");
				}
			}

			System.out.print("Informe o Titulo da música: ");
			String songTitle = scanner.nextLine();

			Optional<Song> song = songRepository.findByTitle(songTitle);
			if (song.isPresent()) {
				song.get().addArtist(artistFounded.get());
				songRepository.save(song.get());
			} else {
				song = Optional.of(new Song(songTitle));
				song.get().addArtist(artistFounded.get());
				songRepository.save(song.get());
			}

			System.out.println(
				TerminalColorUtil.toColor(MenuColor.GREEN, "\nMúsica adicionada")
			);
			System.out.println(TerminalColorUtil.toColor(MenuColor.GRAY, song.get()));

			System.out.print("\nDeseja adicionar mais músicas ao artista? (S/n) ");
			String choice = scanner.nextLine();
			isNewRegister = QuestionUtil.getChoice(choice);

		} while(isNewRegister);
	}

	public void findAll() {
		System.out.println("\nOpção > " + MainMenu.LIST_SONGS);

		List<Song> founded = songRepository.findAllUnique();
		if (founded.isEmpty()) {
			throw new NotFoundException(
				"Não foi encontrado músicas registradas no sistema"
			);
		}
		System.out.println(TerminalColorUtil.toColor(MenuColor.GREEN, "\nLista de músicas"));
		founded.forEach(e -> System.out.println(TerminalColorUtil.toColor(MenuColor.GRAY, e)));
	}

	public void findByArtist() {
		System.out.println("\nOpção > " + MainMenu.SEARCH_SONGS_BY_ARTIST);
		boolean	isNewConsult = true;

		do {
			System.out.print("Informe o nome do Artista: ");
			String artistName = scanner.nextLine();

			List<Song> founded = songRepository.findByArtistNameContainingIgnoreCase(artistName);
			if (founded.isEmpty()) {
				throw new NotFoundException(
					"Não foi encontrado músicas do Artista informado"
				);
			}

			System.out.println(
				TerminalColorUtil.toColor(MenuColor.GREEN, "\nMúsicas encontradas")
			);
			founded.forEach(e -> System.out.println(
				TerminalColorUtil.toColor(MenuColor.GRAY, e)
			));

			System.out.print("\nRealizar nova consulta? (S/n) ");
			String choice = scanner.nextLine();
			isNewConsult = QuestionUtil.getChoice(choice);

		} while (isNewConsult);

	}
}
