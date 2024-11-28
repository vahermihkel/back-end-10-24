package ee.ken.seosed.repository;

import ee.ken.seosed.entity.Auto;
import ee.ken.seosed.entity.Omanik;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OmanikRepository extends JpaRepository<Omanik, Long> {
}
