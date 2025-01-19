package com.jolyvert.deliveryapp.service;


import com.jolyvert.deliveryapp.model.User;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.function.Function;

public interface JwtService {
    public String generateAccessToken(User user);
    public String generateRefreshToken(User user);
    public <T> T extractClaim(String token, Function<Claims, T> resolver);
    public String extractUsername(String token);
    public boolean isValid(String token, UserDetails user);
    public boolean isValidRefresh(String token, User user);
}
