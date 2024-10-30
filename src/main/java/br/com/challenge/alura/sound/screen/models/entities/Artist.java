package br.com.challenge.alura.sound.screen.models.entities;

import br.com.challenge.alura.sound.screen.models.enums.ArtistType;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "artists")
public class Artist {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String name;

	@Enumerated(EnumType.STRING)
	private ArtistType type;

	@Column(columnDefinition = "TEXT")
	private String description;

	@ManyToMany(mappedBy = "artists", fetch = FetchType.LAZY)
	private Set<Song> songs = new HashSet<>();

	public Artist() {
	}

	public Artist(String name,
				  ArtistType type) {
		this.name = name;
		this.type = type;
	}

	public Artist(String name,
				  ArtistType type,
				  String description) {
		this.name = name;
		this.type = type;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public ArtistType getType() {
		return type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Song> getSongs() {
		return songs;
	}

	public void addSong(Song song) {
		songs.add(song);
	}

	@Override
	public String toString() {
		return "Artista: " + name +
				", Tipo: " + type;
	}
}
