package ee.ken.seosed.repository;

import ee.ken.seosed.entity.Auto;
import ee.ken.seosed.entity.Lemmikloom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LemmikloomRepository extends JpaRepository<Lemmikloom, Long> {
}
