package com.jolyvert.deliveryapp.service;


import com.jolyvert.deliveryapp.dto.RegisterCustomerDto;
import com.jolyvert.deliveryapp.model.Customer;
import com.jolyvert.deliveryapp.repository.CustomerRepository;
import com.jolyvert.deliveryapp.repository.FoodCartRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    private final FoodCartService foodCartService;

    public CustomerService(CustomerRepository customerRepository, FoodCartService foodCartService) {
        this.customerRepository = customerRepository;
        this.foodCartService = foodCartService;
    }

    public Customer createCustomer(RegisterCustomerDto registerCustomerDto) {

        Customer customer = new Customer();
        customer.setName(registerCustomerDto.getName());
        customer.setEmail(registerCustomerDto.getEmail());
        customer.setPassword(registerCustomerDto.getPassword());
        customer.setAddress(registerCustomerDto.getAddress());

        foodCartService.createFoodCart(customer);

        return customerRepository.save(customer);
    }


    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    public String deleteCustomer(Long id) {
        customerRepository.delete(customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found")));
        return "Customer deleted";

    }

}


