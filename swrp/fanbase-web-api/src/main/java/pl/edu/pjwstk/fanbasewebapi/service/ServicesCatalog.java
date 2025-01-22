package pl.edu.pjwstk.fanbasewebapi.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Getter
@RequiredArgsConstructor
public class ServicesCatalog implements IServicesCatalog {
    private final StarWarsCharacterService starWarsCharacterService;
    private final PlanetService planetService;
    private final SpeciesService speciesService;
    private final FilmService filmService;
}
