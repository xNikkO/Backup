package pl.edu.pjwstk.fanbasedata.repositories;

public interface IRepositoriesCatalog {
    StarWarsCharacterRepository getStarWarsCharacters();
    FilmRepository getFilms();
    PlanetRepository getPlanets();
    SpeciesRepository getSpecies();
    UserRepository getUsers();
}
