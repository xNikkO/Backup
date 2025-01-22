package pl.edu.pjwstk.fanbaseswapiclient.swapiclient;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.edu.pjwstk.fanbaseswapiclient.Contract.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Component
public class SWAPIClient implements ISWAPIClient {
    private final RestTemplate restTemplate;

    public SWAPIClient() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public List<StarWarsCharacterDTO> getStarWarsCharacters() {
        return getListOfEntitiesFromSwapi("people", StarWarsCharacterDTO.class);
    }

    @Override
    public List<PlanetDTO> getPlanets() {
        return getListOfEntitiesFromSwapi("planets", PlanetDTO.class);
    }

    @Override
    public List<SpeciesDTO> getSpecies() {
        return getListOfEntitiesFromSwapi("species", SpeciesDTO.class);
    }

    @Override
    public List<FilmDTO> getFilms() {
        return getListOfEntitiesFromSwapi("films", FilmDTO.class);
    }

    private <T extends SWDTO> List<T> getListOfEntitiesFromSwapi(String entityName, Class<T> type){
        var url = "https://swapi.py4e.com/api/" + entityName + "/";
        boolean success = true;
        List<T> entities = new ArrayList<>();
        Gson gson = new Gson();
        Type listType = TypeToken.getParameterized(List.class, type).getType();

        while (success) {
            var response = restTemplate.exchange(
                    (url),
                    HttpMethod.GET,
                    null,
                    String.class);


            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                var entity = response.getBody();
                JsonObject jsonObject = gson.fromJson(response.getBody(), JsonObject.class);
                if (jsonObject.has("next") && jsonObject.get("next").isJsonNull()) {
                    success = false;
                } else {
                    url = jsonObject.get("next").getAsString();
                }
                JsonArray jsonArray = jsonObject.getAsJsonArray("results");
                List<T> results = gson.fromJson(jsonArray, listType);
                entities.addAll(results);
            } else {
                success = false;
            }
        }
        return entities;
    }
}
