package br.com.challenge.alura.sound.screen.models.entities;

import jakarta.persistence.*;
import org.hibernate.LazyInitializationException;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "songs")
public class Song {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String title;

	@ManyToMany
	@JoinTable(
		name = "songs_of_artists",
		joinColumns = @JoinColumn(name = "song_id"),
		inverseJoinColumns = @JoinColumn(name = "artist_id")
	)
	private Set<Artist> artists = new HashSet<>();

	public Song() {
	}

	public Song(String title) {
		this.title = title;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public Set<Artist> getArtists() {
		return artists;
	}

	public void addArtist(Artist artist) {
		artists.add(artist);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Titulo: ").append(title);
		try {
			if (!artists.isEmpty()) {
				artists.forEach(e -> builder
						.append(", Artista: ")
						.append(e.getName())
				);
			}
		} catch (LazyInitializationException e) {
		}
		return builder.toString();
	}
}
