package pl.edu.pjwstk.fanbasewebapi.modelDTO;

import lombok.Data;
import lombok.experimental.Accessors;
import pl.edu.pjwstk.fanbasedata.model.Gender;

@Data
@Accessors(chain = true)
public class StarWarsCharacterDTO {
    private Long id;
    private String name;
    private String birthYear;
    private String eyeColor;
    private Gender gender;
    private String hairColor;
    private int height;
    private int weight;
    private String skinColor;
    private Long homeworldId;
    private Long speciesId;
    private Long swqpiId;

}
