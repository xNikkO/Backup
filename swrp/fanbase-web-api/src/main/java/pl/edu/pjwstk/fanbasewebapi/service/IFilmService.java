package pl.edu.pjwstk.fanbasewebapi.service;

import pl.edu.pjwstk.fanbasewebapi.modelDTO.FilmDTO;

import java.util.List;

public interface IFilmService extends IAlterDataService<FilmDTO> {
    List<FilmDTO> getAllFilms();
    List<FilmDTO> getFilmsByTitle(String title);
    FilmDTO getFilmById(Long id);
}
