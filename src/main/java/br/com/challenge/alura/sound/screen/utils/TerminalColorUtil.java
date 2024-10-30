package br.com.challenge.alura.sound.screen.utils;

import br.com.challenge.alura.sound.screen.models.menus.MenuColor;

public class TerminalColorUtil {
	public static String toColor(MenuColor color, Object colorizeContent) {
		return "%s%s%s".formatted(color, colorizeContent.toString(), MenuColor.RESET);
	}
}
