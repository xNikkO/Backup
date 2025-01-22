package pl.edu.pjwstk.fanbasewebapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pjwstk.fanbasedata.model.Film;
import pl.edu.pjwstk.fanbasedata.model.Planet;
import pl.edu.pjwstk.fanbasedata.model.StarWarsCharacter;
import pl.edu.pjwstk.fanbasedata.repositories.RepositoriesCatalog;
import pl.edu.pjwstk.fanbasewebapi.modelDTO.FilmDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FilmService implements IFilmService {
    private final RepositoriesCatalog db;
    @Override
    public List<FilmDTO> getAllFilms() {
        return mapFromListOfFilms(db.getFilms().findAll());
    }

    @Override
    public List<FilmDTO> getFilmsByTitle(String title) {
        return mapFromListOfFilms(db.getFilms().findByTitle(title));
    }

    @Override
    public FilmDTO getFilmById(Long id) {
        var film = db.getFilms().findById(id).orElse(null);
        if (film == null) {
            return null;
        }
        return mapFromFilm(film);
    }

    @Override
    public FilmDTO delete(Long id) {
        var film = db.getFilms().findById(id).orElse(null);

        if (film != null) {
            db.getFilms().delete(film);
            return mapFromFilm(film);
        } else {
            return null;

        }
    }

    @Override
    public FilmDTO update(Long id, FilmDTO filmDTO) {
        var film = db.getFilms().findById(id).orElse(null);

        if (film != null) {
            db.getFilms().save(getFilmFromDto(filmDTO, film));
            return mapFromFilm(film);
        } else {
            return null;
        }
    }

    @Override
    public Long save(FilmDTO filmDTO) {
        Film film = getFilmFromDto(filmDTO);
        var f = db.getFilms().save(film);

        return f.getId();
    }

    private Film getFilmFromDto(FilmDTO filmDTO) {
        return getFilmFromDto(filmDTO, new Film());
    }

    private Film getFilmFromDto(FilmDTO filmDTO, Film film) {
        film.setId(filmDTO.getId());
        film.setTitle(filmDTO.getTitle());
        film.setReleaseDate(filmDTO.getReleaseDate());
        film.setOpeningCrawl(filmDTO.getOpeningCrawl());
        film.setEpisodeId(filmDTO.getEpisodeId());
        film.setCharactersInMovie(
                filmDTO.getCharaterIds()
                        .stream()
                        .map(id -> db.getStarWarsCharacters().findById(id).orElse(null))
                        .collect(Collectors.toSet())
        );
        film.setPlanetsInMovie(
                filmDTO.getPlanetsIds()
                        .stream()
                        .map(id -> db.getPlanets().findById(id).orElse(null))
                        .collect(Collectors.toSet())
        );
        film.setSwapiId(filmDTO.getSwapiId());

        return film;
    }

    private static FilmDTO mapFromFilm(Film film) {
        return new FilmDTO()
                .setId(film.getId())
                .setTitle(film.getTitle())
                .setDirector(film.getDirector())
                .setEpisodeId(film.getEpisodeId())
                .setOpeningCrawl(film.getOpeningCrawl())
                .setReleaseDate(film.getReleaseDate())
                .setCharaterIds(
                        film.getCharactersInMovie().stream()
                                .map(StarWarsCharacter::getId)
                                .collect(Collectors.toSet())
                )
                .setPlanetsIds(
                        film.getPlanetsInMovie().stream()
                        .map(Planet::getId)
                        .collect(Collectors.toSet())
                )
                .setSwapiId(film.getSwapiId());
    }

    private List<FilmDTO> mapFromListOfFilms(List<Film> films) {
        return films.stream().map(FilmService::mapFromFilm).toList();
    }
}
