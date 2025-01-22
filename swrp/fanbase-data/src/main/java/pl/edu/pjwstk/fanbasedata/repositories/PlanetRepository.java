package pl.edu.pjwstk.fanbasedata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pjwstk.fanbasedata.model.Planet;

import java.util.List;

public interface PlanetRepository extends JpaRepository<Planet, Long> {
    List<Planet> findByName(String name);
    boolean existsBySwapiId(Long swapiId);
    Planet findBySwapiId(Long swapiId);
}
