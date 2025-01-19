package com.jolyvert.deliveryapp.service;

import com.jolyvert.deliveryapp.exception.CustomerException;
import com.jolyvert.deliveryapp.model.Customer;

import java.util.List;

public interface CustomerService {
    public List<Customer> findAllCustomers();
    public Customer updateCustomer(int itemId, Customer customer)  throws CustomerException;
    public String deleteCustomer(Long id)  throws CustomerException;
}
