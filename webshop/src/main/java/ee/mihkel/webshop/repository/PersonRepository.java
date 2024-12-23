package ee.mihkel.webshop.repository;

import ee.mihkel.webshop.entity.Category;
import ee.mihkel.webshop.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findByUsername(String username);
}
