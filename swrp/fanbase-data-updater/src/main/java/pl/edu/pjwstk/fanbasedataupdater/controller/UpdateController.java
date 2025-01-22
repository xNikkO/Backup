package pl.edu.pjwstk.fanbasedataupdater.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.edu.pjwstk.fanbasedataupdater.updater.IDataUpdater;

@Controller
@RequestMapping("update/")
@RequiredArgsConstructor
public class UpdateController {
    private final IDataUpdater dataUpdater;

    @GetMapping("all")
    public ResponseEntity<Void> updateAll( ) {
        dataUpdater.updatePlanet();
        dataUpdater.updateSpecies();
        dataUpdater.updateStarWarsCharacter();
        dataUpdater.updateFilms();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("characters")
    public ResponseEntity<Void> updateCharacters() {
        dataUpdater.updateStarWarsCharacter();
        return ResponseEntity.noContent().build();
    }
    @GetMapping("planets")
    public ResponseEntity<Void> updatePlanets() {
        dataUpdater.updatePlanet();
        return ResponseEntity.noContent().build();
    }
    @GetMapping("species")
    public ResponseEntity<Void> updateSpecies() {
        dataUpdater.updateSpecies();
        return ResponseEntity.noContent().build();
    }
    @GetMapping("films")
    public ResponseEntity<Void> updateFilms() {
        dataUpdater.updateFilms();
        return ResponseEntity.noContent().build();
    }
}
