package pl.edu.pjwstk.fanbasedataupdater.updater.mappers;

import org.springframework.stereotype.Component;
import pl.edu.pjwstk.fanbasedata.model.Film;
import pl.edu.pjwstk.fanbaseswapiclient.Contract.FilmDTO;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class FilmMapper implements IMap<FilmDTO, Film> {
    @Override
    public Film toEntity(FilmDTO filmDTO) {
        Film film = new Film();
        film.setDirector(filmDTO.getDirector());
        film.setTitle(filmDTO.getTitle());
        film.setEpisodeId(filmDTO.getEpisodeId());
        film.setOpeningCrawl(filmDTO.getOpeningCrawl());

        try {
            String dateAsString = filmDTO.getReleaseDate();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            Date date = formatter.parse(dateAsString);
            film.setReleaseDate(date);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return film;
    }
}
