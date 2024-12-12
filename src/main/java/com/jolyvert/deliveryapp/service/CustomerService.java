package com.jolyvert.deliveryapp.service;


import com.jolyvert.deliveryapp.dto.RegisterCustomerDto;
import com.jolyvert.deliveryapp.exception.CustomerException;
import com.jolyvert.deliveryapp.exception.FoodCartException;
import com.jolyvert.deliveryapp.exception.LoginException;
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

    public Customer createCustomer(RegisterCustomerDto registerCustomerDto) throws CustomerException {

        if(customerRepository.existsByEmail(registerCustomerDto.getEmail())) {
            throw new CustomerException("Customer already exists");
        }

        Customer customer = new Customer();
        customer.setName(registerCustomerDto.getName());
        customer.setTelephoneNumber(registerCustomerDto.getTelephoneNumber());
        customer.setEmail(registerCustomerDto.getEmail());
        customer.setPassword(registerCustomerDto.getPassword());
        customer.setAddress(registerCustomerDto.getAddress());

        foodCartService.createFoodCart(customer);

        return customerRepository.save(customer);
    }


    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer updateCustomer(int itemId, Customer customer) throws CustomerException {
        Customer newCustomer = customerRepository.findById((long)itemId).orElseThrow (() -> new CustomerException("Customer not found"));
        newCustomer.setName(customer.getName());
        newCustomer.setTelephoneNumber(customer.getTelephoneNumber());
        newCustomer.setEmail(customer.getEmail());
        newCustomer.setPassword(customer.getPassword());
        newCustomer.setAddress(customer.getAddress());
        return customerRepository.save(newCustomer);
    }

    public String deleteCustomer(Long id) throws CustomerException {

        if(!customerRepository.existsById(id)) {
            throw new CustomerException("Customer does not exist");
        }

        customerRepository.delete(customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found")));
        return "Customer deleted";

    }

}


