package com.jolyvert.deliveryapp.service;

import com.jolyvert.deliveryapp.dto.AuthenticationResponseDto;
import com.jolyvert.deliveryapp.dto.LoginRequestDto;
import com.jolyvert.deliveryapp.dto.RegistrationRequestDto;
import com.jolyvert.deliveryapp.exception.LoginException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {

    public void register(RegistrationRequestDto request) throws LoginException;
    public AuthenticationResponseDto authenticate(LoginRequestDto loginDto);
    public ResponseEntity<AuthenticationResponseDto> refreshToken(
            HttpServletRequest request,
            HttpServletResponse response);

}
