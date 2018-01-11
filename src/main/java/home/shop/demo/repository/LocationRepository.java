package home.shop.demo.repository;

import home.shop.demo.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, String>{
}
