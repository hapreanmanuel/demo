package home.shop.demo.repository;

import home.shop.demo.domain.ProductStock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductStockRepository extends JpaRepository<ProductStock, String>{

    List<ProductStock> findAllByLocationId(String locationId);

    List<ProductStock> findAllByProductId(String productId);

    ProductStock findByLocationIdAndProductId(String locationId, String productId);

}
