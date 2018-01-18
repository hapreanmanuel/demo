package home.shop.demo.service;

import home.shop.demo.domain.ProductStock;
import home.shop.demo.repository.ProductStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductStockService {

    @Autowired
    ProductStockRepository productStockRepository;

    @Autowired
    ProductService productService;

    public ProductStockService (ProductStockRepository productStockRepository) {
        this.productStockRepository = productStockRepository;
    }


    public ProductStock getProductStock(String locationId, String productId){ return productStockRepository.findByLocationIdAndProductId(locationId,productId);}

    public List<ProductStock> getAllProductStocks(){ return productStockRepository.findAll();}

    public List<ProductStock> getStockForLocation(String locationId){return productStockRepository.findAllByLocationId(locationId);}

    public void addProductStock(ProductStock productStock){ productStockRepository.save(productStock); }

    public void updateProductStock(ProductStock productStock){ productStockRepository.save(productStock); }

    public void save(List<ProductStock> productStockList) { productStockRepository.save(productStockList);}

    public void deleteStockForLocation(String locationId){ productStockRepository.delete(productStockRepository.findAllByLocationId(locationId)); }

    private int getTotalStockForProduct(String productId){
        return productStockRepository.findAllByProductId(productId).stream()
                .mapToInt(ProductStock::getQuantity)
                .sum();
    }

    //The List has location field set to 'TOTALS'. This list will not be persisted in the database because it is not an actual location.
    public List<ProductStock> totalProductStocks(){
        return productService.getAllProducts()
                .stream()
                .map(product -> new ProductStock("TOTALS", product.getProductId(), getTotalStockForProduct(product.getProductId())))
                .collect(Collectors.toList());
    }
}
