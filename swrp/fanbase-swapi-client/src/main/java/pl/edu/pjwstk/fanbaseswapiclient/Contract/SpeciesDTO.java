package pl.edu.pjwstk.fanbaseswapiclient.Contract;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class SpeciesDTO extends SWDTO {
    @SerializedName("average_height")
    private String averageHeight;
    @SerializedName("average_lifespan")
    private String averageLifespan;
    private String classification;
    private String created;
    private String designation;
    private String edited;
    @SerializedName("eye_colors")
    private String eyeColors;
    @SerializedName("hair_colors")
    private String hairColors;
    private String homeworld;
    private String language;
    private String name;
    private List<String> people;
    private List<String> films;
    @SerializedName("skin_colors")
    private String skinColors;
    private String url;
}

