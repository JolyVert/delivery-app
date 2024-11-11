package com.jolyvert.deliveryapp.controller;

import com.jolyvert.deliveryapp.dto.LoginDto;
import com.jolyvert.deliveryapp.exception.LoginException;
import com.jolyvert.deliveryapp.model.CurrentLoginSession;
import com.jolyvert.deliveryapp.service.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<CurrentLoginSession> login(@RequestBody LoginDto loginDto) throws LoginException {
        return ResponseEntity.ok(loginService.login(loginDto));
    }

}
