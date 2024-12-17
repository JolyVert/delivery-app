package com.jolyvert.deliveryapp.model;

import com.jolyvert.deliveryapp.model.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(length = 30, unique = true, nullable = false)
    private String email;

    @Column(length = 30, nullable = false)
    private String password;

    @Column(length = 30, unique = true, nullable = false)
    private String telephoneNumber;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @Enumerated(EnumType.STRING)
    private Role role;

}
