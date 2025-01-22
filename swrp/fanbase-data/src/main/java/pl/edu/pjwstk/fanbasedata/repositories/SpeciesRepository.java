package pl.edu.pjwstk.fanbasedata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pjwstk.fanbasedata.model.Species;

import java.util.List;

public interface SpeciesRepository extends JpaRepository<Species, Long> {
    List<Species> findByName(String name);
    boolean existsBySwapiId(Long swapiId);
    Species findBySwapiId(Long swapiId);
}
