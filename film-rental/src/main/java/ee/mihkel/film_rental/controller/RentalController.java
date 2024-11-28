package ee.mihkel.film_rental.controller;

import ee.mihkel.film_rental.entity.Film;
import ee.mihkel.film_rental.entity.Rental;
import ee.mihkel.film_rental.repository.FilmRepository;
import ee.mihkel.film_rental.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class RentalController {

    int bonusPoints = 0;

    @Autowired
    RentalRepository rentalRepository;

    @Autowired
    FilmRepository filmRepository;

    @PostMapping("start-rental") // tuleb saata listi filmidest, ID ja INITIALRENTDAYS igaühel
    public List<Rental> startRental(@RequestBody List<Film> films) {
        for (Film film: films) {
            Film dbFilm = filmRepository.findById(film.getId()).orElseThrow();
            if (!dbFilm.isAvailable()) {
                return rentalRepository.findAll(); // returniga lõppeb funktsioon
            }
        }

        Rental rental = new Rental();
        rental.setStartDate(new Date()); // praeguse kuupäeva ja kellaaja
        rental.setLastChangeDate(null);
        rental.setLateFee(0);
        //rental.setSum(calculateSum(films));
        int sum = 0;
        Rental rentalWithId = rentalRepository.save(rental); // pärast andmebaasi salvestust tekib klassile ID

        for (Film film: films) {
            Film dbFilm = filmRepository.findById(film.getId()).orElseThrow();
            film.setName(dbFilm.getName()); // võin saata nime ka, aga seda pole mõtet
            film.setType(dbFilm.getType()); // võin saata tüübi ka, aga seda pole mõtet
            film.setInitialRentDays(film.getInitialRentDays()); // selle võtan front-endist. front-endist tuleb see + ID
            film.setFinalRentDays(0); // selle pean ka panema, muidu äkki kirjutab front-end muu arvu (aga andmebaasis juba on 0)
            film.setAvailable(false); // seda muudan
            film.setRental(rentalWithId); // seda muudan
            sum = sum + calculateSum(film);
            filmRepository.save(film); // tehakse küll .save(), aga tegelikult toimub update
        }
        rental.setSum(sum);
        rentalRepository.save(rental);

        return rentalRepository.findAll();
    }

    private int calculateSum(Film film) {
        int basic_price = 3;
        int premium_price = 4;
        int sum = 0; // seda suurendame
//        for (Film film: films) {
            switch (film.getType()) {
                //6 päeva: 3 + 3*1 = 6€
                case "Old" -> {
                    if (film.getInitialRentDays() <= 5) {
                        sum = sum + basic_price;
                    } else {      // esimesed 5         iga ülejäänud päev
                        sum = sum + basic_price + basic_price * (film.getInitialRentDays() - 5);
                    }
                }
                //6 päeva: 3 + 3*3 = 12€
                case "Regular" -> {
                    if (film.getInitialRentDays() <= 3) {
                        sum = sum + basic_price;
                    } else {
                        sum = sum + basic_price + basic_price * (film.getInitialRentDays() - 3);
                    }
                }
                //6 päeva: 6*4 = 24€
                case "New" -> sum = sum + film.getInitialRentDays() * premium_price;
            }
//        }
        return sum;
    }

    // localhost:8080/end-rental?filmId=4&rentDays=10
    @PostMapping("end-rental")
    public List<Rental> endRental(@RequestParam Long filmId, int rentDays) {
        Film film = filmRepository.findById(filmId).get();
//        film.setFinalRentDays(rentDays);

        Rental rental = rentalRepository.findById(film.getRental().getId()).get();
        rental.setLastChangeDate(new Date());
        if (rentDays > film.getInitialRentDays()) {
            rental.setLateFee(rental.getLateFee() + calculateLateFee(film, rentDays));
        }

        film.setInitialRentDays(0);
        film.setAvailable(true);
        film.setRental(null);
        filmRepository.save(film);
        rentalRepository.save(rental);

        return rentalRepository.findAll();
    }

    private int calculateLateFee(Film film, int rentDays) {
        if (film.getType().equals("New")) {
            return (rentDays - film.getInitialRentDays()) * 4;
        } else if (film.getType().equals("Old") || film.getType().equals("Regular")) {
            return (rentDays - film.getInitialRentDays()) * 3;
        } else {
            return 0;
        }
    }
}