package pl.edu.pjwstk.fanbaseclient.modelDTO;

import lombok.Data;
import lombok.experimental.Accessors;

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
