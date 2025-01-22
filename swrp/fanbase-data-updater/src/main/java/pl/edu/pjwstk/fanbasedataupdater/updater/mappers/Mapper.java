package pl.edu.pjwstk.fanbasedataupdater.updater.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.edu.pjwstk.fanbasedata.model.Film;
import pl.edu.pjwstk.fanbasedata.model.Planet;
import pl.edu.pjwstk.fanbasedata.model.Species;
import pl.edu.pjwstk.fanbasedata.model.StarWarsCharacter;
import pl.edu.pjwstk.fanbaseswapiclient.Contract.FilmDTO;
import pl.edu.pjwstk.fanbaseswapiclient.Contract.PlanetDTO;
import pl.edu.pjwstk.fanbaseswapiclient.Contract.SpeciesDTO;
import pl.edu.pjwstk.fanbaseswapiclient.Contract.StarWarsCharacterDTO;

@Component
@RequiredArgsConstructor
public class Mapper implements IMapper {
    private final StarWarsCharacterMapper starWarsCharacter;
    private final PlanetMapper planetMapper;
    private final SpeciesMapper speciesMapper;
    private final FilmMapper filmMapper;
    @Override
    public IMap<StarWarsCharacterDTO, StarWarsCharacter> starWarsCharacter() {
        return starWarsCharacter;
    }

    @Override
    public IMap<PlanetDTO, Planet> planet() {
        return planetMapper;
    }

    @Override
    public IMap<SpeciesDTO, Species> species() {
        return speciesMapper;
    }

    @Override
    public IMap<FilmDTO, Film> film() {
        return filmMapper;
    }
}
