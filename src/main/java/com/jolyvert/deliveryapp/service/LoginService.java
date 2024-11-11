package com.jolyvert.deliveryapp.service;


import com.jolyvert.deliveryapp.dto.LoginDto;
import com.jolyvert.deliveryapp.exception.LoginException;
import com.jolyvert.deliveryapp.model.CurrentLoginSession;
import com.jolyvert.deliveryapp.model.Customer;
import com.jolyvert.deliveryapp.repository.CustomerRepository;
import com.jolyvert.deliveryapp.repository.SessionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.UUID.*;

import java.util.Optional;

@Service
public class LoginService {
    private final CustomerRepository customerRepository;
    private final SessionRepository sessionRepository;

    public LoginService(CustomerRepository customerRepository, SessionRepository sessionRepository) {
        this.customerRepository = customerRepository;
        this.sessionRepository = sessionRepository;
    }

    public CurrentLoginSession login(LoginDto loginDto) throws LoginException {

        Customer customer = customerRepository.findByEmail(loginDto.getEmail());

        if(customer == null) {
            throw new LoginException("User not found");
        }

        if(sessionRepository.findById(customer.getCustomerId()).isPresent()) {
            throw new LoginException("Already logged in");
        }

        if(customer.getPassword().equals(loginDto.getPassword())) {

            String key = UUID.randomUUID().toString();
            CurrentLoginSession currentLoginSession = new CurrentLoginSession(customer.getCustomerId(), key, LocalDateTime.now());

            return sessionRepository.save(currentLoginSession);


        } else {
            throw new LoginException("Wrong password");
        }
    }


    public String logout(String key) throws LoginException {

        CurrentLoginSession currentLoginSession = sessionRepository.findByUuid(key);

        if(currentLoginSession == null) {
            throw new LoginException("User not logged in");
        }
        sessionRepository.delete(currentLoginSession);

        return "Logged out";
    }
}
