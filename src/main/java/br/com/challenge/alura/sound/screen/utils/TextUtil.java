package br.com.challenge.alura.sound.screen.utils;

public class TextUtil {
	public static String breakText(String content, Integer charAmountOfBreak) {
		String newContent = content.replace("\n", "");

		StringBuilder builder = new StringBuilder();
		Integer count = 0;

		for (char step : newContent.toCharArray()) {
			builder.append(step);
			count++;

			if (count.equals(charAmountOfBreak)) {
				builder.append('\n');
				count = 0;
			}
		}

		return builder.toString();
	}
}
