package pl.edu.pjwstk.fanbasewebapi.service;

import pl.edu.pjwstk.fanbasewebapi.modelDTO.StarWarsCharacterDTO;

import java.util.List;

public interface ICharacterService extends IAlterDataService<StarWarsCharacterDTO> {
    List<StarWarsCharacterDTO> getAllCharacters();
    List<StarWarsCharacterDTO> getCharactersByName(String name);

    StarWarsCharacterDTO getCharacterById(Long id);
}
