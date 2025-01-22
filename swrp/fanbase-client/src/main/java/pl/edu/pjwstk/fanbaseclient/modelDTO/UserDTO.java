package pl.edu.pjwstk.fanbaseclient.modelDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserDTO {
    @JsonProperty(defaultValue = "0")
    private Long id;
    private String nickname;
    private String login;
    private String password;
    @JsonProperty("favourite_character_id")
    private Long favouriteCharacterId;
    @JsonProperty("favourite_planet_id")
    private Long favouritePlanetId;
    @JsonProperty("favourite_movie_id")
    private Long favouriteMovieId;
    private Long swqpiId;

}
