package home.shop.demo.repository;

import home.shop.demo.domain.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductcategoryRepository extends JpaRepository<ProductCategory, String>{
}
