package com.jolyvert.deliveryapp.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int restaurantId;

    @Column(length = 30)
    String restaurantName;

    @Column(length = 15)
    String telephoneNumber;

    @OneToOne(cascade = CascadeType.ALL)
    Address address;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Item> itemList = new ArrayList<Item>();

}
