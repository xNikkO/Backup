package pl.edu.pjwstk.fanbasedataupdater.updater.mappers;

import org.springframework.stereotype.Component;
import pl.edu.pjwstk.fanbasedata.model.Species;
import pl.edu.pjwstk.fanbaseswapiclient.Contract.SpeciesDTO;

@Component
public class SpeciesMapper implements IMap<SpeciesDTO, Species> {
    @Override
    public Species toEntity(SpeciesDTO speciesDTO) {
        Species species = new Species();
        species.setName(speciesDTO.getName());
        species.setDesignation(speciesDTO.getDesignation());
        species.setLanguage(speciesDTO.getLanguage());
        species.setClassification(speciesDTO.getClassification());
        species.setAverageHeight(NumberPraser.prase(speciesDTO.getAverageHeight(), Double.class));
        species.setAverageLifespan(NumberPraser.prase(speciesDTO.getAverageLifespan(), Long.class));

        return species;

    }
}
