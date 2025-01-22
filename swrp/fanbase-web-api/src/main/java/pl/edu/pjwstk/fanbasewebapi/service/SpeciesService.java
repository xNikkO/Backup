package pl.edu.pjwstk.fanbasewebapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pjwstk.fanbasedata.model.Planet;
import pl.edu.pjwstk.fanbasedata.model.Species;
import pl.edu.pjwstk.fanbasedata.repositories.RepositoriesCatalog;
import pl.edu.pjwstk.fanbasewebapi.modelDTO.SpeciesDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpeciesService implements ISpeciesService {
    private final RepositoriesCatalog db;


    @Override
    public List<SpeciesDTO> getAllSpecies() {
        return mapFromListOfSpecies(db.getSpecies().findAll());
    }

    @Override
    public List<SpeciesDTO> getSpeciesByName(String speciesName) {
        return mapFromListOfSpecies(db.getSpecies().findByName(speciesName));
    }

    @Override
    public SpeciesDTO getSpeciesById(Long id) {
        var species = db.getSpecies().findById(id).orElse(null);
        if (species == null)
            return null;
        return mapFromSpecies(species);
    }

    @Override
    public SpeciesDTO delete(Long id) {
        var species = db.getSpecies().findById(id).orElse(null);

        if (species != null) {
            db.getSpecies().deleteById(id);
            return mapFromSpecies(species);
        } else {
            return null;
        }
    }

    @Override
    public SpeciesDTO update(Long id, SpeciesDTO speciesDTO) {
        Species species = db.getSpecies().findById(id).orElse(null);
        if (species != null) {
            db.getSpecies().save(getSpeciesFromDTO(speciesDTO, species));
            return mapFromSpecies(species);
        } else {
            return null;
        }

    }

    @Override
    public Long save(SpeciesDTO speciesDTO) {
        Species species = getSpeciesFromDTO(speciesDTO);
        var s = db.getSpecies().save(species);
        return s.getId();
    }
    private Species getSpeciesFromDTO(SpeciesDTO speciesDTO) {
        return getSpeciesFromDTO(speciesDTO, new Species());
    }
    private Species getSpeciesFromDTO(SpeciesDTO speciesDTO, Species species) {
        species.setId(speciesDTO.getId());
        species.setName(speciesDTO.getName());
        species.setAverageLifespan(speciesDTO.getAverageLifespan());
        species.setAverageHeight(speciesDTO.getAverageHeight());
        species.setLanguage(speciesDTO.getLanguage());
        species.setClassification(speciesDTO.getClassification());
        species.setDesignation(speciesDTO.getDesignation());

        Planet planet = db.getPlanets().findById(speciesDTO.getOriginPlanetId()).orElse(null);

        species.setOriginPlanet(planet);
        species.setSwapiId(speciesDTO.getId());

        return species;
    }

    private static SpeciesDTO mapFromSpecies(Species species) {
        return new SpeciesDTO()
                .setId(species.getId())
                .setName(species.getName())
                .setAverageHeight(species.getAverageHeight())
                .setAverageLifespan(species.getAverageLifespan())
                .setLanguage(species.getLanguage())
                .setClassification(species.getClassification())
                .setDesignation(species.getDesignation())
                .setOriginPlanetId(species.getOriginPlanet() != null ? species.getOriginPlanet().getId() : null )
                .setSwqpiId(species.getSwapiId());
    }

    private List<SpeciesDTO> mapFromListOfSpecies(List<Species> speciesList) {
        return speciesList.stream().map(SpeciesService::mapFromSpecies).toList();
    }
}
