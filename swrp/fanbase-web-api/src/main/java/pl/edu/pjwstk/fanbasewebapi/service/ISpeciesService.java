package pl.edu.pjwstk.fanbasewebapi.service;

import pl.edu.pjwstk.fanbasewebapi.modelDTO.SpeciesDTO;

import java.util.List;

public interface ISpeciesService extends IAlterDataService<SpeciesDTO> {
    List<SpeciesDTO> getAllSpecies();
    List<SpeciesDTO> getSpeciesByName(String speciesName);
    SpeciesDTO getSpeciesById(Long id);
}
