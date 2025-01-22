package pl.edu.pjwstk.fanbaseswapiclient.swapiclient;

import pl.edu.pjwstk.fanbaseswapiclient.Contract.FilmDTO;
import pl.edu.pjwstk.fanbaseswapiclient.Contract.PlanetDTO;
import pl.edu.pjwstk.fanbaseswapiclient.Contract.SpeciesDTO;
import pl.edu.pjwstk.fanbaseswapiclient.Contract.StarWarsCharacterDTO;

import java.util.List;

public interface ISWAPIClient {
    List<StarWarsCharacterDTO> getStarWarsCharacters();
    List<PlanetDTO> getPlanets();
    List<SpeciesDTO> getSpecies();
    List<FilmDTO> getFilms();
}
