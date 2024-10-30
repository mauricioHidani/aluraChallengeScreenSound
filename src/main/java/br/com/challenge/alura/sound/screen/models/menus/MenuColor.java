package br.com.challenge.alura.sound.screen.models.menus;

public enum MenuColor {
	RESET("\u001B[0m"),
	RED("\u001B[31m"),
	GREEN("\u001B[32m"),
	YELLOW("\u001B[33m"),
	BLUE("\u001B[34m"),
	PURPLE("\u001B[35m"),
	CYAN("\u001B[36m"),
	WHITE("\u001B[37m"),
	GRAY("\u001B[90m"),
	;

	private final String code;

	MenuColor(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return code;
	}
}
