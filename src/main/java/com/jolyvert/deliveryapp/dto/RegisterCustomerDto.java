package com.jolyvert.deliveryapp.dto;

import com.jolyvert.deliveryapp.model.Address;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterCustomerDto {

    private String email;

    private String telephoneNumber;

    private String password;

    private String name;

    private Address address;

}
