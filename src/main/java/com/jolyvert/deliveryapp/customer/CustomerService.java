package com.jolyvert.deliveryapp.customer;


import org.apache.catalina.User;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;

    public CustomerService(CustomerRepository customerRepository, AddressRepository addressRepository) {
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
    }

    public Customer createCustomer(RegisterCustomerDto registerCustomerDto) {

        Customer customer = new Customer();
        customer.setName(registerCustomerDto.getName());
        customer.setEmail(registerCustomerDto.getEmail());
        customer.setPassword(registerCustomerDto.getPassword());
        customer.setAddress(registerCustomerDto.getAddress());



        return customerRepository.save(customer);
    }



}
