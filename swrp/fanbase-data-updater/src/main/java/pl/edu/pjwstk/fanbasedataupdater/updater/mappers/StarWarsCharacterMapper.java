package pl.edu.pjwstk.fanbasedataupdater.updater.mappers;


import org.springframework.stereotype.Component;
import pl.edu.pjwstk.fanbasedata.model.Gender;
import pl.edu.pjwstk.fanbasedata.model.StarWarsCharacter;
import pl.edu.pjwstk.fanbaseswapiclient.Contract.StarWarsCharacterDTO;

@Component
public class StarWarsCharacterMapper implements IMap<StarWarsCharacterDTO, StarWarsCharacter> {
    @Override
    public StarWarsCharacter toEntity(StarWarsCharacterDTO starWarsCharacterDTO) {
        var starWarsCharacter = new StarWarsCharacter();

        starWarsCharacter.setName(starWarsCharacterDTO.getName());
        starWarsCharacter.setHeight(NumberPraser.prase(starWarsCharacterDTO.getHeight(), Integer.class));
        starWarsCharacter.setWeight(NumberPraser.prase(starWarsCharacterDTO.getMass(), Integer.class));
        starWarsCharacter.setHairColor(starWarsCharacterDTO.getHairColor());
        starWarsCharacter.setSkinColor(starWarsCharacterDTO.getSkinColor());
        starWarsCharacter.setEyeColor(starWarsCharacterDTO.getEyeColor());
        starWarsCharacter.setBirthYear(starWarsCharacterDTO.getBirthYear());

        switch (starWarsCharacterDTO.getGender()){
            case "male":
                starWarsCharacter.setGender(Gender.MALE);
                break;
            case "female":
                starWarsCharacter.setGender(Gender.FEMALE);
                break;
            case "unknown":
                starWarsCharacter.setGender(Gender.UNKNOWN);
                break;
            default:
                starWarsCharacter.setGender(Gender.NA);
        }
        return starWarsCharacter;
    }

}
