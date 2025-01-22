package pl.edu.pjwstk.fanbasedataupdater.updater.mappers;

import org.springframework.stereotype.Component;
import pl.edu.pjwstk.fanbasedata.model.Planet;
import pl.edu.pjwstk.fanbaseswapiclient.Contract.PlanetDTO;

@Component
public class PlanetMapper implements IMap<PlanetDTO, Planet> {

    @Override
    public Planet toEntity(PlanetDTO planetDTO) {
        var planet = new Planet();

        planet.setName(planetDTO.getName());
        planet.setDiameter(NumberPraser.prase(planetDTO.getDiameter(), Integer.class));
        planet.setRotationPeriod(NumberPraser.prase(planetDTO.getRotationPeriod(), Integer.class));
        planet.setOrbitalPeriod(NumberPraser.prase(planetDTO.getOrbitalPeriod(), Integer.class));
        var gravity = planetDTO.getGravity().replace("standard","").trim();
        planet.setGravity(NumberPraser.prase(gravity, Double.class));
        planet.setPopulation(NumberPraser.prase(planetDTO.getPopulation(), Double.class));
        planet.setClimate(planetDTO.getClimate());
        planet.setTerrain(planetDTO.getTerrain());
        planet.setSurfaceWaterPercentage(NumberPraser.prase(planetDTO.getSurfaceWater(), Integer.class));
        return planet;
    }

}
