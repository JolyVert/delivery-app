package com.jolyvert.deliveryapp.controller;


import com.jolyvert.deliveryapp.dto.AuthenticationResponseDto;
import com.jolyvert.deliveryapp.dto.LoginRequestDto;
import com.jolyvert.deliveryapp.dto.RegistrationRequestDto;
import com.jolyvert.deliveryapp.exception.LoginException;
import com.jolyvert.deliveryapp.service.AuthenticationService;
import com.jolyvert.deliveryapp.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    private final UserService userService;

    public AuthenticationController(AuthenticationService authenticationService, UserService userService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegistrationRequestDto registerDto) throws LoginException {


        authenticationService.register(registerDto);

        return ResponseEntity.ok("Registered");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDto> authenticate(
            @RequestBody LoginRequestDto loginDto) {

        return ResponseEntity.ok(authenticationService.authenticate(loginDto));
    }

    @PostMapping("/refresh_token")
    public ResponseEntity<AuthenticationResponseDto> refreshToken(
            HttpServletRequest request,
            HttpServletResponse response) {

        return authenticationService.refreshToken(request, response);
    }

}
