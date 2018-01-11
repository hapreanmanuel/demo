package home.shop.demo.service;

import home.shop.demo.domain.ProductStock;
import home.shop.demo.domain.ProductStockKey;
import home.shop.demo.repository.ProductStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductStockService {

    @Autowired
    ProductStockRepository productStockRepository;

    public ProductStockService (ProductStockRepository productStockRepository) {
        this.productStockRepository = productStockRepository;
    }

    public ProductStock getProductStock(ProductStockKey productStockKey) { return productStockRepository.findOne(productStockKey);}

    public List<ProductStock> getAllProductStocks(){ return productStockRepository.findAll();}

    public List<ProductStock> getStockForLocation(String locationId){return productStockRepository.findAllByLocationId(locationId);}

    public void addProductStock(ProductStock productStock){ productStockRepository.save(productStock); }

    public void updateProductStock(ProductStock productStock){ productStockRepository.save(productStock); }

    public void save(List<ProductStock> productStockList) { productStockRepository.save(productStockList);}

    public void deleteProductStock(ProductStockKey productStockKey){ productStockRepository.delete(productStockKey);}

    public void deleteStockForLocation(String locationId){ productStockRepository.delete(productStockRepository.findAllByLocationId(locationId)); }


}
