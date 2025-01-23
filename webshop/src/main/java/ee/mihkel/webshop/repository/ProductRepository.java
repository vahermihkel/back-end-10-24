package ee.mihkel.webshop.repository;

import ee.mihkel.webshop.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findByActiveTrueOrderByIdAsc(Pageable pageable);

    Page<Product> findByCategory_Id(Long id, Pageable pageable);

}
