package com.jolyvert.deliveryapp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "foodCart")
public class FoodCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartId;

    @OneToOne(cascade = CascadeType.ALL)
    private Customer customer;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Item> itemList = new ArrayList<>();

}
