package pl.edu.pjwstk.fanbasewebapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pjwstk.fanbasedata.model.Planet;
import pl.edu.pjwstk.fanbasedata.model.Species;
import pl.edu.pjwstk.fanbasedata.model.StarWarsCharacter;
import pl.edu.pjwstk.fanbasedata.repositories.IRepositoriesCatalog;
import pl.edu.pjwstk.fanbasewebapi.modelDTO.StarWarsCharacterDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StarWarsCharacterService implements ICharacterService {
    private final IRepositoriesCatalog db;

    @Override
    public List<StarWarsCharacterDTO> getAllCharacters() {
        return mapFromListOfStarWarsCharacters(db.getStarWarsCharacters().findAll());
    }

    @Override
    public List<StarWarsCharacterDTO> getCharactersByName(String name) {
        return mapFromListOfStarWarsCharacters(db.getStarWarsCharacters().findByName(name));
    }

    @Override
    public StarWarsCharacterDTO getCharacterById(Long id) {
        var character = db.getStarWarsCharacters().findById(id).orElse(null);
        if (character == null)
            return null;

        return mapFromCharacter(character);
    }

    @Override
    public StarWarsCharacterDTO delete(Long id) {
        var character = db.getStarWarsCharacters().findById(id).orElse(null);
        if (character != null) {
            db.getStarWarsCharacters().delete(character);
            return mapFromCharacter(character);
        } else {
            return null;
        }
    }

    @Override
    public StarWarsCharacterDTO update(Long id, StarWarsCharacterDTO dtoEntity) {
        var character = db.getStarWarsCharacters().findById(id).orElse(null);
        if (character != null) {
            setForeignObjects(character, dtoEntity);
            db.getStarWarsCharacters().save(getStarWarsCharacterFromDto(dtoEntity, character));
            return mapFromCharacter(character);
        } else {
            return null;
        }
    }

    @Override
    public Long save(StarWarsCharacterDTO dtoCharacter) {
        StarWarsCharacter character = getStarWarsCharacterFromDto(dtoCharacter);
        setForeignObjects(character, dtoCharacter);
        var ch = db.getStarWarsCharacters().save(character);
        return ch.getId();
    }

    private void setForeignObjects(StarWarsCharacter character, StarWarsCharacterDTO dtoCharacter) {
        Planet homeworld = dtoCharacter.getHomeworldId() == null ?
                null : db.getPlanets().findById(dtoCharacter.getHomeworldId()).orElse(null);
        character.setHomeworld(homeworld);


        Species species = dtoCharacter.getSpeciesId() == null ?
                null : db.getSpecies().findById(dtoCharacter.getSpeciesId()).orElse(null);
        character.setSpecies(species);
    }

    private static StarWarsCharacter getStarWarsCharacterFromDto(StarWarsCharacterDTO dtoCharacter) {
        return getStarWarsCharacterFromDto(dtoCharacter, new StarWarsCharacter());
    }
    private static StarWarsCharacter getStarWarsCharacterFromDto(StarWarsCharacterDTO dtoCharacter, StarWarsCharacter character) {
        character.setId(dtoCharacter.getId());
        character.setName(dtoCharacter.getName());
        character.setBirthYear(dtoCharacter.getBirthYear());
        character.setEyeColor(dtoCharacter.getEyeColor());
        character.setGender(dtoCharacter.getGender());
        character.setHairColor(dtoCharacter.getHairColor());
        character.setHeight(dtoCharacter.getHeight());
        character.setWeight(dtoCharacter.getWeight());
        character.setSkinColor(dtoCharacter.getSkinColor());
        character.setSwapiId(dtoCharacter.getSwqpiId());

        return character;
    }
    private static StarWarsCharacterDTO mapFromCharacter(StarWarsCharacter character) {

        return new StarWarsCharacterDTO()
                .setId(character.getId())
                .setName(character.getName())
                .setEyeColor(character.getEyeColor())
                .setBirthYear(character.getBirthYear())
                .setHeight(character.getHeight())
                .setWeight(character.getWeight())
                .setHairColor(character.getHairColor())
                .setGender(character.getGender())
                .setHomeworldId(character.getHomeworld() != null ? character.getHomeworld().getId() : null )
                .setSpeciesId(character.getSpecies() != null ? character.getSpecies().getId() : null )
                .setSkinColor(character.getSkinColor())
                .setSwqpiId(character.getSwapiId());
    }
    private static List<StarWarsCharacterDTO> mapFromListOfStarWarsCharacters(List<StarWarsCharacter> characters) {
        return characters.stream().map(StarWarsCharacterService::mapFromCharacter).toList();
    }
}
