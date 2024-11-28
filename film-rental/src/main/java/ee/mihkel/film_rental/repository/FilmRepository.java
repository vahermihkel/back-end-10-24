package ee.mihkel.film_rental.repository;

import ee.mihkel.film_rental.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Long> {
}
