package br.com.challenge.alura.sound.screen.repositories;

import br.com.challenge.alura.sound.screen.models.entities.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ArtistRepository extends JpaRepository<Artist, Long> {

	Optional<Artist> findByNameContainingIgnoreCase(String name);

	@Modifying
	@Query("UPDATE Artist a SET a.description = :description WHERE a.id = :id")
	void updateDescription(Long id, String description);
}
