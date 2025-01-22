package pl.edu.pjwstk.fanbasedata.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Setter
@Getter
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String title;
    private int episodeId;
    private String openingCrawl;
    private String director;
    private Date releaseDate;

    @ManyToMany
    @JoinTable(
            name = "characters_in_films",
            joinColumns = @JoinColumn(name = "film_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "star_wars_character_id", nullable = false)
    )
    Set<StarWarsCharacter> charactersInMovie;

    @ManyToMany
    @JoinTable(
            name = "planets_in_films",
            joinColumns = @JoinColumn(name = "film_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "planet_id" , nullable = false)
    )
    Set<Planet> planetsInMovie;

    private Long swapiId;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return episodeId == film.episodeId && Objects.equals(id, film.id) && Objects.equals(title, film.title) && Objects.equals(openingCrawl, film.openingCrawl) && Objects.equals(director, film.director) && Objects.equals(releaseDate, film.releaseDate) && Objects.equals(charactersInMovie, film.charactersInMovie) && Objects.equals(planetsInMovie, film.planetsInMovie) && Objects.equals(swapiId, film.swapiId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, episodeId, openingCrawl, director, releaseDate, charactersInMovie, planetsInMovie, swapiId);
    }
}
