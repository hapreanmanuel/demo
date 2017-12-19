package models;

import lombok.Data;

import javax.persistence.*;
import java.net.URL;
import java.util.Currency;

@Data
@Entity
@Table(name= "Product")
public class Product {

    @Id
    @Column(name="ProductId")
    private final String productId;

    @Column(name="Category")
    private String category;

    @Column(name="MainCategory")
    private String mainCategory;

    @Column(name="Weight")
    private String weight;

    @Column(name="WeightUnit")
    private String weightUnit;

    @Column(name="ShortDescription")
    private String shortDescription;

    @Column(name="Name")
    private String name;

    private URL pictureUrl;

    @Column(name="Status")
    private String status;

    @Column(name="Price")
    private int price;

    @Column(name="DimensionWidth")
    private double dimensionWidth;

    @Column(name="DimensionDepth")
    private double dimensionDepth;

    @Column(name="DimensionHeight")
    private double dimensionHeight;

    @Column(name="Unit")
    private String unit;

    @Column(name="CurrencyCode")
    private Currency currencyCode;

}
