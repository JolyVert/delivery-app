package com.jolyvert.deliveryapp.service;


import com.jolyvert.deliveryapp.exception.CustomerException;
import com.jolyvert.deliveryapp.model.Customer;
import com.jolyvert.deliveryapp.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
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


