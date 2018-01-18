package home.shop.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@IdClass(ProductStockKey.class)
@Table(name="PRODUCTSTOCK")
public class ProductStock {

//    @EmbeddedId
//    private ProductStockKey productStockKey;
//
//    public void setProductStockKey(ProductStockKey productStockKey) {
//        this.productStockKey = productStockKey;
//        setProductId(productStockKey.getProductId());
//        setLocationId(productStockKey.getLocationId());
//    }
//
//    public void setProductStockKey(Product product, Location location) {
//        setProductStockKey(keyFromProductAndLocation(product,location));
//    }
//
//    public ProductStockKey keyFromProductAndLocation(Product product, Location location) {
//        return new ProductStockKey(product.getProductId(), location.getLocationId());
//    }

    @Id
    @Column(name = "LOCATIONID")
    private String locationId;

    @Id
    @Column(name = "PRODUCTID")
    private String productId;

    @Basic
    @Column(name = "QUANTITY")
    private int quantity;

}

@AllArgsConstructor
@NoArgsConstructor
@Data
class ProductStockKey implements Serializable{

    private String productId;

    private String locationId;

}