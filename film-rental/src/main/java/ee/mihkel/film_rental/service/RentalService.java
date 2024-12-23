package ee.mihkel.film_rental.service;

import ee.mihkel.film_rental.entity.Film;
import org.springframework.stereotype.Service;

@Service // --> et saaksin @Autowired teha
public class RentalService { // abiline klass, kuhu saab Controllerist funktsioone tõsta

    public int calculateSum(Film film) {
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

    public int calculateLateFee(Film film, int rentDays) {
        if (film.getType().equals("New")) {
            return (rentDays - film.getInitialRentDays()) * 4;
        } else if (film.getType().equals("Old") || film.getType().equals("Regular")) {
            return (rentDays - film.getInitialRentDays()) * 3;
        } else {
            return 0;
        }
    }

}
