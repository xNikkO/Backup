package pl.edu.pjwstk.fanbasewebapi.service;

public interface IServicesCatalog {
    ICharacterService getStarWarsCharacterService();
    IPlanetService getPlanetService();
    ISpeciesService getSpeciesService();
    IFilmService getFilmService();
}
