package home.shop.demo.repository;

import home.shop.demo.domain.ProductStock;
import home.shop.demo.domain.ProductStockKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductStockRepository extends JpaRepository<ProductStock, ProductStockKey>{
    List<ProductStock> findAllByLocationId(String locationId);

    List<ProductStock> findAllByProductId(String productId);

}
