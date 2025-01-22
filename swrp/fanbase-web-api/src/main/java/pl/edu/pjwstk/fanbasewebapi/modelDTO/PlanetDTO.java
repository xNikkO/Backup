package pl.edu.pjwstk.fanbasewebapi.modelDTO;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PlanetDTO {
    private Long id;
    private String name;
    private int diameter;
    private int rotationPeriod;
    private int orbitalPeriod;
    private double gravity;
    private double population;
    private String climate;
    private String terrain;
    private int surfaceWaterPercentage;
    private Long swqpiId;

}
