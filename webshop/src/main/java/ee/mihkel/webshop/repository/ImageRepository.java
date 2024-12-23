package ee.mihkel.webshop.repository;

import ee.mihkel.webshop.entity.Category;
import ee.mihkel.webshop.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
