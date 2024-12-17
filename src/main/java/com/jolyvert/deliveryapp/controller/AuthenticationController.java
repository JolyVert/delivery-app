package com.jolyvert.deliveryapp.controller;

import com.jolyvert.deliveryapp.dto.LoginDto;
import com.jolyvert.deliveryapp.dto.RegisterDto;
import com.jolyvert.deliveryapp.exception.LoginException;
import com.jolyvert.deliveryapp.model.CurrentLoginSession;
import com.jolyvert.deliveryapp.model.User;
import com.jolyvert.deliveryapp.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<CurrentLoginSession> login(@RequestBody LoginDto loginDto) throws LoginException {
        return ResponseEntity.ok(authenticationService.login(loginDto));
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterDto registerDto) throws LoginException {
        return ResponseEntity.ok(authenticationService.register(registerDto));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestParam(required = false) String key) throws LoginException {
        return ResponseEntity.ok(authenticationService.logout(key));
    }

}
