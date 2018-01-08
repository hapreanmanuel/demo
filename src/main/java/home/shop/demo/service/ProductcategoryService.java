package home.shop.demo.service;

import home.shop.demo.domain.ProductCategory;
import home.shop.demo.repository.ProductcategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductcategoryService {

    @Autowired
    private ProductcategoryRepository productcategoryRepository;

    public ProductcategoryService(ProductcategoryRepository productcategoryRepository){
        this.productcategoryRepository = productcategoryRepository;
    }
    public Iterable<ProductCategory> getAllProductcategory() {
        return productcategoryRepository.findAll();
    }

    public ProductCategory getProductcategory(String category) {
        return productcategoryRepository.findOne(category);
    }

    public void addProductcategory(ProductCategory productCategory) {
        productcategoryRepository.save(productCategory);
    }

    public void deleteProduct(String category) {
        productcategoryRepository.delete(category);
    }

    public void save(List<ProductCategory> productCategories) {
        productcategoryRepository.save(productCategories);
    }

}



