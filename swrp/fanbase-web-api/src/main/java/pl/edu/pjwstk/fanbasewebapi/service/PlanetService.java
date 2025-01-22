package pl.edu.pjwstk.fanbasewebapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pjwstk.fanbasedata.model.Planet;
import pl.edu.pjwstk.fanbasedata.repositories.IRepositoriesCatalog;
import pl.edu.pjwstk.fanbasewebapi.modelDTO.PlanetDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanetService implements IPlanetService {
    private final IRepositoriesCatalog db;

    @Override
    public List<PlanetDTO> getAllPlanets() {
        return mapFromPlanets(db.getPlanets().findAll());
    }

    @Override
    public List<PlanetDTO> getPlanetsByName(String planetName) {
        return mapFromPlanets(db.getPlanets().findByName(planetName));
    }

    @Override
    public PlanetDTO getPlanetById(Long id) {
        var planet = db.getPlanets().findById(id).orElse(null);
        if (planet == null)
            return null;
        return mapFromPlanet(planet);
    }

    @Override
    public PlanetDTO delete(Long id) {
        var planet = db.getPlanets().findById(id).orElse(null);

        if (planet != null){
            db.getPlanets().delete(planet);
            return mapFromPlanet(planet);
        } else {
            return null;
        }
    }

    @Override
    public PlanetDTO update(Long id, PlanetDTO planetDTO) {
        var planet = db.getPlanets().findById(id).orElse(null);

        if (planet != null){
            db.getPlanets().save(getPlanetFromDTO(planetDTO, planet));
            return mapFromPlanet(planet);
        } else {
            return null;
        }
    }

    @Override
    public Long save(PlanetDTO planetDTO) {
        Planet planet = getPlanetFromDTO(planetDTO);
        var p = db.getPlanets().save(planet);
        return p.getId();
    }

    private Planet getPlanetFromDTO(PlanetDTO planetDTO) {
        return getPlanetFromDTO(planetDTO, new Planet());
    }
    private Planet getPlanetFromDTO(PlanetDTO planetDTO, Planet planet) {
        planet.setId(planetDTO.getId());
        planet.setName(planetDTO.getName());
        planet.setClimate(planetDTO.getClimate());
        planet.setPopulation(planetDTO.getPopulation());
        planet.setDiameter(planetDTO.getDiameter());
        planet.setGravity(planetDTO.getGravity());
        planet.setSurfaceWaterPercentage(planetDTO.getSurfaceWaterPercentage());
        planet.setTerrain(planetDTO.getTerrain());
        planet.setOrbitalPeriod(planetDTO.getOrbitalPeriod());
        planet.setRotationPeriod(planetDTO.getRotationPeriod());
        planet.setSwapiId(planetDTO.getId());

        return planet;

    }

    private PlanetDTO mapFromPlanet(Planet planet){
        PlanetDTO planetDTO = new PlanetDTO();
        return planetDTO.setId(planet.getId())
                .setName(planet.getName())
                .setClimate(planet.getClimate())
                .setDiameter(planet.getDiameter())
                .setGravity(planet.getGravity())
                .setOrbitalPeriod(planet.getOrbitalPeriod())
                .setPopulation(planet.getPopulation())
                .setRotationPeriod(planet.getRotationPeriod())
                .setSurfaceWaterPercentage(planet.getSurfaceWaterPercentage())
                .setTerrain(planet.getTerrain())
                .setSwqpiId(planet.getSwapiId());
    }

    private List<PlanetDTO> mapFromPlanets(List<Planet> planets) {
        return planets.stream().map(this::mapFromPlanet).toList();
    }
}
