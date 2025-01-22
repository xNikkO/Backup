package pl.edu.pjwstk.fanbaseclient.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import pl.edu.pjwstk.fanbaseclient.modelDTO.*;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ViewService {
    private final RestClient restClient;

    public List<StarWarsCharacterDTO> getStarWarsCharacters() {
        List<StarWarsCharacterDTO> characters = restClient
                .get()
                .uri("/search/star-wars-character/")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});

        if (characters == null || characters.isEmpty() ) {
            return null;
        }
        return characters;
    }

    public  List<StarWarsCharacterDTO> getStarWarsCharactersByName(String name) {
        List<StarWarsCharacterDTO> characters = restClient
                .get()
                .uri("/search/star-wars-character/name/" + name)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
        if (characters == null || characters.isEmpty() ) {
            return null;
        }
        return characters;
    }

    public StarWarsCharacterDTO getStarWarsCharacterById(Long id) {
        StarWarsCharacterDTO character = restClient
                .get()
                .uri("/search/star-wars-character/id/" + id)
                .retrieve()
                .body(StarWarsCharacterDTO.class);
        if (character == null) {
            return null;
        }
        return character;
    }


    public List<PlanetDTO> getPlanets() {
        List<PlanetDTO> planets = restClient
                .get()
                .uri("search/planets")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
        if (planets == null || planets.isEmpty() ) {
            return null;
        }
        return planets;
    }

    public List<PlanetDTO> getPlanetsByName(String name) {
        List<PlanetDTO> planets = restClient
                .get()
                .uri("/search/planet/name/" + name)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});

        if (planets == null || planets.isEmpty() ) {
            return null;
        }
        return planets;
    }

    public PlanetDTO getPlanetById(Long id) {
        PlanetDTO planet = restClient
                .get()
                .uri("search/planet/id/" + id)
                .retrieve()
                .body(PlanetDTO.class);
        if (planet == null) {
            return null;
        }
        return planet;
    }

    public List<SpeciesDTO> getSpecies() {
        List<SpeciesDTO> species = restClient
                .get()
                .uri("/search/species")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
        if (species == null || species.isEmpty() ) {
            return null;
        }
        return species;
    }

    public List<SpeciesDTO> getSpeciesByName(String name) {
        List<SpeciesDTO> species = restClient
                .get()
                .uri("search/species/name/" + name)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
        if (species == null || species.isEmpty() ) {
            return null;
        }
        return species;
    }

    public SpeciesDTO getSpeciesById(Long id) {
        SpeciesDTO species = restClient
                .get()
                .uri("search/species/id/" + id)
                .retrieve()
                .body(SpeciesDTO.class);
        if (species == null) {
            return null;
        }
        return species;
    }

    public List<FilmDTO> getFilms() {
        List<FilmDTO> films = restClient
                .get()
                .uri("/search/films")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
        if (films == null || films.isEmpty() ) {
            return null;
        }
        return films;
    }

    public List<FilmDTO> getFilmsByTitle(String title) {
        List<FilmDTO> films = restClient
                .get()
                .uri("/search/film/title/" + title)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
        if (films == null || films.isEmpty() ) {
            return null;
        }
        return films;
    }

    public FilmDTO getFilmById(Long id) {
        FilmDTO film = restClient
                .get()
                .uri("search/film/id/" + id)
                .retrieve()
                .body(FilmDTO.class);
        if (film == null) {
            return null;
        }
        return film;
    }

    public void register(UserDTO user, String passwordConfirm) {
        if (passwordConfirm.equals(user.getPassword())){
            restClient.post()
                    .uri("user/register")
                    .body(user)
                    .retrieve()
                    .toBodilessEntity();
        }
    }

    public UserDTO login(UserDTO inputUser) {
        UserDTO  user = restClient
                .post()
                .uri("user/login")
                .body(inputUser)
                .retrieve()
                .body(UserDTO.class);
        if (user != null) {
            return user;
        }

        return null;
    }

    public HttpStatusCode updateUser(UserDTO user) {
        var result = restClient.put()
                .uri("user/update")
                .body(user)
                .retrieve()
                .toBodilessEntity()
                .getStatusCode();

        return result;
    }
}
