package home.shop.demo.domain;

import lombok.Data;

@Data
public class ShippmentDetails {

    private int freight;                        //External service

    private String shipAddress;

    private String shipCity;

    private String shipCountry;

    private String shipName;

    private String shipPostalCode;

    private String shipRegion;

    private int shipVia;                        //External service


}
