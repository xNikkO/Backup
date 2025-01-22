package pl.edu.pjwstk.fanbasedataupdater.updater.mappers;

import pl.edu.pjwstk.fanbasedata.model.Film;
import pl.edu.pjwstk.fanbasedata.model.Planet;
import pl.edu.pjwstk.fanbasedata.model.Species;
import pl.edu.pjwstk.fanbasedata.model.StarWarsCharacter;
import pl.edu.pjwstk.fanbaseswapiclient.Contract.FilmDTO;
import pl.edu.pjwstk.fanbaseswapiclient.Contract.PlanetDTO;
import pl.edu.pjwstk.fanbaseswapiclient.Contract.SpeciesDTO;
import pl.edu.pjwstk.fanbaseswapiclient.Contract.StarWarsCharacterDTO;

public interface IMapper {
    IMap<StarWarsCharacterDTO, StarWarsCharacter> starWarsCharacter();
    IMap<PlanetDTO, Planet> planet();
    IMap<SpeciesDTO, Species> species();
    IMap<FilmDTO, Film> film();
}
