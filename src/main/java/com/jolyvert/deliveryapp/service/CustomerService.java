package com.jolyvert.deliveryapp.service;


import com.jolyvert.deliveryapp.dto.RegisterDto;
import com.jolyvert.deliveryapp.model.Customer;
import com.jolyvert.deliveryapp.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(RegisterDto registerDto) {

        Customer customer = new Customer();
        customer.setName(registerDto.getName());
        customer.setEmail(registerDto.getEmail());
        customer.setPassword(registerDto.getPassword());
        customer.setAddress(registerDto.getAddress());

        return customerRepository.save(customer);
    }


    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }



}
