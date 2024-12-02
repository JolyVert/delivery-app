package com.jolyvert.deliveryapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "foodCart")
public class FoodCart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cartId;

    @OneToOne(cascade = CascadeType.ALL)
    private Customer customer;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Item> itemList = new ArrayList<>();

}
