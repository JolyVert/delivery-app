package com.jolyvert.deliveryapp.model;


import com.jolyvert.deliveryapp.model.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@DiscriminatorValue("RESTAURANT")
public class Restaurant extends User {

    @Column(length = 30, nullable = false)
    private String restaurantName;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Item> itemList;

    public Restaurant() {
        super();
        this.setRole(Role.RESTAURANT);
    }

}
