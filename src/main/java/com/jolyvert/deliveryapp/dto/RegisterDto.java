package com.jolyvert.deliveryapp.dto;

import com.jolyvert.deliveryapp.model.Address;
import com.jolyvert.deliveryapp.model.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterDto {

    private String email;

    private String telephoneNumber;

    private String password;

    private String name;

    private Address address;

    private Role role;

}
