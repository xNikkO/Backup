package pl.edu.pjwstk.fanbaseclient.modelDTO;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SpeciesDTO {
    private Long id;
    private String name;
    private String classification;
    private String designation;
    private double averageHeight;
    private long averageLifespan;
    private String language;
    private Long originPlanetId;
    private Long swqpiId;

}
