package com.jolyvert.deliveryapp.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int addressId;

    @Column(length = 50, nullable = false)
    private String country;

    @Column(length = 50, nullable = false)
    private String city;

    @Column(length = 50, nullable = false)
    private String street;

    @Column(length = 5, nullable = false)
    private int buildingNumber;

    @Column(length = 5)
    private int floorNumber;

    @Column(length = 5)
    private int apartmentNumber;

    @Column(length = 10)
    private String postalCode;


}
