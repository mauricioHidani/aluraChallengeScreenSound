package br.com.challenge.alura.sound.screen.utils;

public class QuestionUtil {
	public static boolean getChoice(String choice) {
		switch (choice.toUpperCase()) {
			case "S", "SIM" -> { return true; }
			case "N", "NAO", "NÃO" -> { return false; }
			default -> throw new IllegalArgumentException("Escolha inválida");
		}
	}
}
