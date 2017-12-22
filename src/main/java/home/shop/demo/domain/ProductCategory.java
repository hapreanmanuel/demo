package home.shop.demo.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
@Table(name="PRODUCTCATEGORY")
public class ProductCategory {

    @Id
    @Column(name="CATEGORY")
    private String category;

    @Basic
    @Column(name="CATEGORYNAME")
    private String categoryName;

    @Basic
    @Column(name="NUMBEROFPRODUCTS")
    private int numberOfProducts;
}
