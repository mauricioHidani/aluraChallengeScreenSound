package br.com.challenge.alura.sound.screen.repositories;

import br.com.challenge.alura.sound.screen.models.entities.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SongRepository extends JpaRepository<Song, Long> {

	Optional<Song> findByTitle(String title);

	@Query("SELECT DISTINCT s FROM Song s JOIN s.artists a")
	List<Song> findAllUnique();

	@Query("SELECT s, a FROM Song s JOIN FETCH s.artists a WHERE a.name ILIKE %:artistName%")
	List<Song> findByArtistNameContainingIgnoreCase(String artistName);
}
