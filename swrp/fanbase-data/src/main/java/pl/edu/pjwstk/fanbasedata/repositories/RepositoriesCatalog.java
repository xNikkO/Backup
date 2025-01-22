package pl.edu.pjwstk.fanbasedata.repositories;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@Getter
@RequiredArgsConstructor
public class RepositoriesCatalog implements IRepositoriesCatalog {
    private final StarWarsCharacterRepository starWarsCharacters;
    private final FilmRepository films;
    private final PlanetRepository planets;
    private final SpeciesRepository species;
    private final UserRepository users;
}
