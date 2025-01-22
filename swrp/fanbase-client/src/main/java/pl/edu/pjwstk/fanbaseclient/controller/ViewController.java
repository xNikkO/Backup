package pl.edu.pjwstk.fanbaseclient.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjwstk.fanbaseclient.modelDTO.*;
import pl.edu.pjwstk.fanbaseclient.service.ViewService;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequiredArgsConstructor
@RequestMapping("fanbase/")
public class ViewController {
    private final ViewService viewService;

    @GetMapping("main")
    public String mainPage() {
        return "mainFBPage";
    }
    @PostMapping("main")
    public String postMain(@RequestParam("searchParam") String searchParameter){
        return "redirect:results/named/"+searchParameter;
    }

    @GetMapping("results/named/{searchParameter}")
    public String resultsPage(Model model, @PathVariable String searchParameter) {
        var characters = viewService.getStarWarsCharactersByName(searchParameter);
        var planets = viewService.getPlanetsByName(searchParameter);
        var species = viewService.getSpeciesByName(searchParameter);
        var films = viewService.getFilmsByTitle(searchParameter);

        model.addAttribute("characters", characters);
        model.addAttribute("planets", planets);
        model.addAttribute("speciesList", species);
        model.addAttribute("films", films);
        return "resultFBPage";
    }

    @GetMapping("character/id/{id}")
    public String characterPage(@PathVariable Long id, Model model) {
        StarWarsCharacterDTO character = viewService.getStarWarsCharacterById(id);
        PlanetDTO planet = null;
        Long homewordId = character.getHomeworldId();

        if (homewordId != null) {
            planet = viewService.getPlanetById(homewordId);
        }
        SpeciesDTO species = null;

        Long speciesId = character.getSpeciesId();
        if (speciesId != null) {
            species = viewService.getSpeciesById(speciesId);
        }
        model.addAttribute("character", character);
        model.addAttribute("homeword", planet);
        model.addAttribute("species", species);

        return "characterPage";
    }

    @GetMapping("planet/id/{id}")
    public String planetPage(@PathVariable Long id, Model model) {
        PlanetDTO planet = viewService.getPlanetById(id);

        model.addAttribute("planet", planet);
        return "planetPage";

    }

    @GetMapping("species/id/{id}")
    public String speciesPage(@PathVariable Long id, Model model) {
        SpeciesDTO species = viewService.getSpeciesById(id);

        PlanetDTO planet = null;

        Long originPlanetId = species.getOriginPlanetId();
        if (originPlanetId != null) {
            planet = viewService.getPlanetById(originPlanetId);
        }
        model.addAttribute("species", species);
        model.addAttribute("originPlanet", planet);
        return "speciesPage";
    }

    @GetMapping("film/id/{id}")
    public String filmPage(@PathVariable Long id, Model model) {
        FilmDTO film = viewService.getFilmById(id);
        Set<StarWarsCharacterDTO> charactersInFilm = new HashSet<>();

        film.getCharaterIds().forEach(characterId -> {
            StarWarsCharacterDTO character = viewService.getStarWarsCharacterById(characterId);
            charactersInFilm.add(character);
        });

        Set<PlanetDTO> planetsInFilm = new HashSet<>();
        film.getPlanetsIds().forEach(planetId -> {
            PlanetDTO planet = viewService.getPlanetById(planetId);
            planetsInFilm.add(planet);
        });
        model.addAttribute("film", film);
        model.addAttribute("charactersInFilm", charactersInFilm);
        model.addAttribute("planetsInFilm", planetsInFilm);
        return "filmPage";
    }

    @GetMapping("register")
    public String registerPage(Model model) {
        model.addAttribute("user", new UserDTO());
        return "registerToFB";
    }

    @PostMapping("register")
    public String postRegister(@ModelAttribute("user") UserDTO user, @RequestParam("passwordConfirm") String passwordConfirm) {
        viewService.register(user, passwordConfirm);
        return "redirect:main";
    }

    @GetMapping("login")
    public String loginPage(Model model) {
        model.addAttribute("user", new UserDTO());
        return "loginToFB";
    }

    @PostMapping("login")
    public String postLogin(@ModelAttribute("user") UserDTO user, HttpSession session) {
        UserDTO loggedUser = viewService.login(user);

        if (loggedUser != null) {
            session.setAttribute("loggedUser", loggedUser);
            return "redirect:main";
        }
        return "redirect:login";
    }

    @GetMapping("logout")
    public String logout(HttpSession session) {
        session.removeAttribute("loggedUser");
        return "redirect:main";
    }

    @PostMapping("add/favourite/character")
    public String addFavouriteCharacter(@RequestParam("id") Long id, HttpSession session) {
        UserDTO updateUser = (UserDTO) session.getAttribute("loggedUser");
        updateUser.setFavouriteCharacterId(id);
        var result = viewService.updateUser(updateUser);

        if (result == HttpStatus.BAD_REQUEST) {
            session.setAttribute("loggedUser", updateUser);
        }
        return "redirect:../../character/id/" + id;

    }
    @PostMapping("add/favourite/planet")
    public String addFavouritePlanet(@RequestParam("id") Long id, HttpSession session) {
        UserDTO updateUser = (UserDTO) session.getAttribute("loggedUser");
        updateUser.setFavouritePlanetId(id);
        var result = viewService.updateUser(updateUser);

        if (result == HttpStatus.BAD_REQUEST) {
            session.setAttribute("loggedUser", updateUser);
        }
        return "redirect:../../planet/id/" + id;

    }

    @PostMapping("add/favourite/film")
    public String addFavouriteFilm(@RequestParam("id") Long id, HttpSession session) {
        UserDTO updateUser = (UserDTO) session.getAttribute("loggedUser");
        updateUser.setFavouriteMovieId(id);
        var result = viewService.updateUser(updateUser);

        if (result == HttpStatus.BAD_REQUEST) {
            session.setAttribute("loggedUser", updateUser);
        }
        return "redirect:../../film/id/" + id;
    }

    @GetMapping("profile")
    public String profilePage(Model model, HttpSession session) {
        if (session.getAttribute("loggedUser") == null) {
            return "redirect:main";
        }
        UserDTO loggedUser = (UserDTO) session.getAttribute("loggedUser");

        StarWarsCharacterDTO favouriteCharacter = null;
        var favCharacterId = loggedUser.getFavouriteCharacterId();

        if (favCharacterId != null) {
           favouriteCharacter = viewService.getStarWarsCharacterById(favCharacterId);

        }

        PlanetDTO favouritePlanet = null;
        var favPlanetId = loggedUser.getFavouritePlanetId();

        if (favPlanetId != null) {
            favouritePlanet = viewService.getPlanetById(favPlanetId);
        }

        FilmDTO favouriteMovie = null;
        var favMovieId = loggedUser.getFavouriteMovieId();

        if (favMovieId != null) {
            favouriteMovie = viewService.getFilmById(favMovieId);
        }
        model.addAttribute("favChar", favouriteCharacter);
        model.addAttribute("favPlanet", favouritePlanet);
        model.addAttribute("favMovie", favouriteMovie);
        return "profilePage";

    }

}
