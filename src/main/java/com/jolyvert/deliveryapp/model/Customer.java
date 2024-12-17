package com.jolyvert.deliveryapp.model;

import com.jolyvert.deliveryapp.model.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Setter
@Entity
@DiscriminatorValue("CUSTOMER")
public class Customer extends User {

    @Column(length = 20, nullable = false)
    private String name;

    public Customer() {
        super();
        this.setRole(Role.CUSTOMER);
    }



}
