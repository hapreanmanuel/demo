package models;

import lombok.Data;

import javax.persistence.Id;

@Data
public class ProductCategory {

    @Id
    private final String category;
    private String categoryName;
    private int numberOfProducts;
}
