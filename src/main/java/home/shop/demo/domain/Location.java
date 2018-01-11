package home.shop.demo.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="LOCATION")
public class Location {

    @Id
    @Column(name="LOCATIONID")
    private String locationId;

    @Basic
    @Column(name="LOCATIONADDRESS")
    private String locationAddress;

    @Basic
    @Column(name="LOCATIONCITY")
    private String locationCity;

    @Basic
    @Column(name="LOCATIONCOUNTRY")
    private String locationCountry;

    @Basic
    @Column(name="LOCATIONPOSTALCODE")
    private String locationPostalCode;

    @Basic
    @Column(name="LOCATIONREGION")
    private String locationRegion;


}


