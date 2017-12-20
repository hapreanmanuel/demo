package models;

import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.RowMapper;

import javax.persistence.*;
import java.sql.ResultSet;
import java.sql.SQLException;



@Data
@Entity
@Table(name= "PRODUCT")
public class Product {

    @Id
    @Column(name="ProductId")
    private final String productId;

    @Column(name="Category")
    private String category;

    @Column(name="MainCategory")
    private String mainCategory;

    @Column(name="SupplierName")
    private String supplierName;

    @Column(name="Weight")
    private float weight;

    @Column(name="WeightUnit")
    private String weightUnit;

    @Column(name="ShortDescription")
    private String shortDescription;

    @Column(name="Name")
    private String name;

    @Column(name="PictureURL")
    private String pictureUrl;

    @Column(name="Status")
    private String status;

    @Column(name="Price")
    private float price;

    @Column(name="DimensionWidth")
    private float dimensionWidth;

    @Column(name="DimensionDepth")
    private float dimensionDepth;

    @Column(name="DimensionHeight")
    private float dimensionHeight;

    @Column(name="Unit")
    private String unit;

    @Column(name="CurrencyCode")
    private String currencyCode;

}

class ProductMapper implements RowMapper<Product> {
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException{
        Product product = new Product(rs.getString("PRODUCTID"));
        product.setCategory(rs.getString("CATEGORY"));
        product.setMainCategory(rs.getString("MAINCATEGORY"));
        product.setSupplierName(rs.getString("SUPPLIERNAME"));
        product.setWeight(rs.getFloat("WEIGHT"));
        product.setWeightUnit(rs.getString("WEIGHTUNIT"));
        product.setShortDescription(rs.getString("SHORTDESCRIPTION"));
        product.setName(rs.getString("NAME"));
        product.setPictureUrl(rs.getString("PICTUREURL"));
        product.setStatus(rs.getString("STATUS"));
        product.setPrice(rs.getFloat("PRICE"));
        product.setDimensionWidth(rs.getFloat("DIMENSIONWIDTH"));
        product.setDimensionDepth(rs.getFloat("DIMENSIONDEPTH"));
        product.setDimensionHeight(rs.getFloat("DIMENSIONHEIGHT"));
        product.setUnit(rs.getString("UNIT"));
        product.setCurrencyCode(rs.getString("CURRENCYCODE"));

        return product;
    }
}

@Configuration
@EnableJpaRepositories(basePackageClasses = Product.class,
        entityManagerFactoryRef = "productEntityManagerFactory")
class ProductConfiguration {

}