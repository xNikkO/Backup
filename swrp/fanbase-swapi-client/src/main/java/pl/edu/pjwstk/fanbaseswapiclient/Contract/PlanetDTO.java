package pl.edu.pjwstk.fanbaseswapiclient.Contract;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class PlanetDTO extends SWDTO {
    private String climate;
    private String created;
    private String diameter;
    private String edited;
    private List<String> films;
    private String gravity;
    private String name;
    @SerializedName("orbital_period")
    private String orbitalPeriod;
    private String population;
    private List<String> residents;
    @SerializedName("rotation_period")
    private String rotationPeriod;
    @SerializedName("surface_water")
    private String surfaceWater;
    private String terrain;
    private String url;
}

