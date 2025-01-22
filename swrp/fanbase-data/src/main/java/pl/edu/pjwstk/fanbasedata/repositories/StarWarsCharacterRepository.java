package pl.edu.pjwstk.fanbasedata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pjwstk.fanbasedata.model.StarWarsCharacter;

import java.util.List;

public interface StarWarsCharacterRepository extends JpaRepository<StarWarsCharacter, Long> {
    List<StarWarsCharacter> findByName(String name);
    boolean existsBySwapiId(Long swapiId);
    StarWarsCharacter findBySwapiId(Long swapiId);
}
