package br.com.challenge.alura.sound.screen.models.menus;

import br.com.challenge.alura.sound.screen.utils.TerminalColorUtil;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public enum MainMenu {
	DEFAULT("Padrão"),
	REGISTER_ARTIST("Cadastrar artistas"),
	REGISTER_SONG("Cadastrar músicas"),
	LIST_SONGS("Listar músicas"),
	SEARCH_SONGS_BY_ARTIST("Buscar músicas por artistas"),
	SEARCH_DATA_ABOUT_ARTIST("Pesquisar dados sobre um artista"),
	EXIST("Sair"),
	;

	private final String label;

	MainMenu(String label) {
		this.label = label;
	}

	@Override
	public String toString() {
		return label;
	}

	public static void showMenu(MenuColor listColor, MenuColor contentColor) {
		AtomicInteger count = new AtomicInteger(1);
		Arrays.stream(MainMenu.values()).skip(1).forEach(e ->
			System.out.printf(
				"\t%s. %s\n",
				TerminalColorUtil.toColor(listColor, count.getAndIncrement()),
				TerminalColorUtil.toColor(contentColor, e)
			)
		);
	}
}
