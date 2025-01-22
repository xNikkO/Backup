package pl.edu.pjwstk.fanbaseclient.modelDTO;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.Set;

@Data
@Accessors(chain = true)
public class FilmDTO {
    private Long id;
    private String title;
    private int episodeId;
    private String openingCrawl;
    private String director;
    private Date releaseDate;
    Set<Long> charaterIds;
    Set<Long> planetsIds;
    private Long swapiId;
}
