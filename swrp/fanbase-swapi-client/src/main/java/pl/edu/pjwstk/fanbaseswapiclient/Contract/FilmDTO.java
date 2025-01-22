package pl.edu.pjwstk.fanbaseswapiclient.Contract;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class FilmDTO extends SWDTO {
    private List<String> characters;
    private String created;
    private String director;
    private String edited;
    @SerializedName("episode_id")
    private int episodeId;
    @SerializedName("opening_crawl")
    private String openingCrawl;
    private List<String> planets;
    private String producer;
    @SerializedName("release_date")
    private String releaseDate;
    private List<String> species;
    private List<String> starships;
    private String title;
    private String url;
    private List<String> vehicles;
}

