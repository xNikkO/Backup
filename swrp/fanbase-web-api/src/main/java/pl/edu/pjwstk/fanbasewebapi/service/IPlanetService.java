package pl.edu.pjwstk.fanbasewebapi.service;

import pl.edu.pjwstk.fanbasewebapi.modelDTO.PlanetDTO;

import java.util.List;

public interface IPlanetService extends IAlterDataService<PlanetDTO> {
    List<PlanetDTO> getAllPlanets();
    List<PlanetDTO> getPlanetsByName(String planetName);
    PlanetDTO getPlanetById(Long planetId);
}
