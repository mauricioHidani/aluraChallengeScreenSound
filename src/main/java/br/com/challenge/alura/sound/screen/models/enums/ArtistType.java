package br.com.challenge.alura.sound.screen.models.enums;

import java.util.Arrays;
import java.util.Optional;

public enum ArtistType {
	SOLO("Solo"),
	DUO("Dupla"),
	BAND("Banda")
	;

	private final String label;

	ArtistType(String label) {
		this.label = label;
	}

	@Override
	public String toString() {
		return label;
	}

	public Boolean equalsTo(String label) {
		String labelNormalized = label.toUpperCase().trim();
		String typeNormalized = this.label.toUpperCase().trim();

		return typeNormalized.contains(labelNormalized);
	}

	public static ArtistType convertFrom(String label) {
		Optional<ArtistType> result = Arrays.stream(ArtistType.values())
				.filter(e -> e.equalsTo(label))
				.findFirst();
		if (!result.isPresent()) {
			throw new IllegalArgumentException(
				"Não é possível converter o tipo de artista especificado"
			);
		}

		return result.get();
	}

	public static Boolean contains(String label) {
		for (ArtistType type : ArtistType.values()) {
			if (type.equalsTo(label)) {
				return true;
			}
		}
		return false;
	}
}
