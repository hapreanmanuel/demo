package home.shop.demo.domain;

import com.fasterxml.jackson.annotation.JsonFilter;
import home.shop.demo.service.ProductcategoryService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.RowMapper;

import javax.persistence.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;


//@NoArgsConstructor
@Data
@Entity
@Table(name= "PRODUCT")
public class Product {

//    @ManyToOne(optional = true)
//    @JoinColumn(name="CATEGORY")
//    private ProductCategory productCategory;
//
//    public void setProductCategory(ProductCategory productCategory) {
//        this.productCategory = productCategory;
//        setCategory(productCategory.getCategory());
//        setMainCategory(productCategory.getCategoryName());
//    }

    @Id
    @Column(name = "PRODUCTID")
    private String productId;

    @Basic
    @Column(name = "CATEGORY")
    private String category;

    @Basic
    @Column(name = "MAINCATEGORY")
    private String mainCategory;

    @Basic
    @Column(name = "SUPPLIERNAME")
    private String supplierName;

    @Basic
    @Column(name = "WEIGHT")
    private float weight;

    @Basic
    @Column(name = "WEIGHTUNIT")
    private String weightUnit;

    @Basic
    @Column(name = "SHORTDESCRIPTION")
    private String shortDescription;

    @Basic
    @Column(name = "NAME")
    private String name;


    @Column(name = "PICTUREURL")
    private String pictureUrl;

    @Column(name = "STATUS")
    private String status;

    @Basic
    @Column(name = "PRICE")
    private float price;

    @Basic
    @Column(name = "DIMENSIONWIDTH")
    private float dimensionWidth;

    @Basic
    @Column(name = "DIMENSIONDEPTH")
    private float dimensionDepth;

    @Basic
    @Column(name = "DIMENSIONHEIGHT")
    private float dimensionHeight;

    @Basic
    @Column(name = "UNIT")
    private String unit;

    @Basic
    @Column(name = "CURRENCYCODE")
    private String currencyCode;
}