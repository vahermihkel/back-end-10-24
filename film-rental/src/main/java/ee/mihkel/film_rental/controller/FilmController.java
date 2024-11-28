package ee.mihkel.film_rental.controller;

import ee.mihkel.film_rental.entity.Film;
import ee.mihkel.film_rental.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FilmController {

    @Autowired
    FilmRepository filmRepository;

    // TODO: Kui tüüp kirjutatakse valesti, siis viskab errori
    @PostMapping("films")      // tuleb saata NAME ja TYPE --> ülejäänud kirjutatakse üle
    public List<Film> addFilm(@RequestBody Film film) { // Panen Postmani või Front-endi bodysse
        film.setRental(null);
        film.setAvailable(true);
        film.setInitialRentDays(0);
        film.setFinalRentDays(0);
        filmRepository.save(film);
        return filmRepository.findAll();
    }

    @DeleteMapping("films/{id}")
    public List<Film> deleteFilm(@PathVariable Long id) { // PathVariable --> siis peab olema üleval kirjas
        filmRepository.deleteById(id);
        return filmRepository.findAll();
    }

    // patch --> ühe kindla mudeli välja muutmine. siin muudetakse Type
    @PatchMapping("films")
    public List<Film> deleteFilm(@RequestParam Long id, String newType) { // RequestParams -> ?id=1&newType=UUS_TÜÜP
        Film film = filmRepository.findById(id).orElseThrow();
        film.setType(newType);
        filmRepository.save(film);
        return filmRepository.findAll();
    }

    @GetMapping("films")
    public List<Film> allFilms() {
        return filmRepository.findAll();
    }

    @GetMapping("available-films")
    public List<Film> allAvailableFilms() {
        //return filmRepository.findAll();
        List<Film> availableFilms = new ArrayList<>();
        for (Film film: filmRepository.findAll()) {
            if (film.isAvailable()) {
                availableFilms.add(film);
            }
        }
        return availableFilms;
    }
}
