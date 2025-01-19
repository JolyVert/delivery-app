package com.jolyvert.deliveryapp.service;

import com.jolyvert.deliveryapp.dto.AuthenticationResponseDto;
import com.jolyvert.deliveryapp.dto.LoginRequestDto;
import com.jolyvert.deliveryapp.dto.RegistrationRequestDto;
import com.jolyvert.deliveryapp.exception.LoginException;
import com.jolyvert.deliveryapp.model.Customer;
import com.jolyvert.deliveryapp.model.Restaurant;
import com.jolyvert.deliveryapp.model.Token;
import com.jolyvert.deliveryapp.model.User;
import com.jolyvert.deliveryapp.model.enums.Role;
import com.jolyvert.deliveryapp.repository.TokenRepository;
import com.jolyvert.deliveryapp.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;

    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final TokenRepository tokenRepository;

    private final FoodCartService foodCartService;


    public AuthenticationServiceImpl(UserRepository userRepository,
                                 JwtService jwtService,
                                 PasswordEncoder passwordEncoder,
                                 AuthenticationManager authenticationManager,
                                 TokenRepository tokenRepository,
                                 FoodCartService foodCartService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenRepository = tokenRepository;
        this.foodCartService = foodCartService;
    }

    public void register(RegistrationRequestDto registerDto) throws LoginException {

        if(userRepository.existsByEmail(registerDto.getEmail())) {
            throw new LoginException("User already exists");
        }

        if(registerDto.getRole().equals(Role.CUSTOMER)) {
            Customer customer = new Customer();
            customer.setName(registerDto.getName());
            customer.setEmail(registerDto.getEmail());
            customer.setTelephoneNumber(registerDto.getTelephoneNumber());
            customer.setPassword(passwordEncoder.encode(registerDto.getPassword()));
            customer.setRole(Role.CUSTOMER);
            customer.setAddress(registerDto.getAddress());
            foodCartService.createFoodCart(customer);
            userRepository.save(customer);
        } else if(registerDto.getRole().equals(Role.RESTAURANT)) {
            Restaurant restaurant = new Restaurant();
            restaurant.setRestaurantName(registerDto.getName());
            restaurant.setEmail(registerDto.getEmail());
            restaurant.setTelephoneNumber(registerDto.getTelephoneNumber());
            restaurant.setPassword(passwordEncoder.encode(registerDto.getPassword()));
            restaurant.setRole(Role.RESTAURANT);
            restaurant.setAddress(registerDto.getAddress());
            userRepository.save(restaurant);
        } else {
            throw new LoginException("User must be either CUSTOMER or RESTAURANT");
        }

    }

    private void revokeAllToken(User user) {

        List<Token> validTokens = tokenRepository.findAllAccessTokenByUser((long)user.getId());

        if(!validTokens.isEmpty()){
            validTokens.forEach(t ->{
                t.setLoggedOut(true);
            });
        }

        tokenRepository.saveAll(validTokens);
    }

    private void saveUserToken(String accessToken, String refreshToken, User user) {

        Token token = new Token();

        token.setAccessToken(accessToken);
        token.setRefreshToken(refreshToken);
        token.setLoggedOut(false);
        token.setUser(user);

        tokenRepository.save(token);
    }

    public AuthenticationResponseDto authenticate(LoginRequestDto loginDto) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()
                )
        );

        User user = userRepository.findByEmail(loginDto.getUsername())
                .orElseThrow();

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        revokeAllToken(user);

        saveUserToken(accessToken, refreshToken, user);

        return new AuthenticationResponseDto(accessToken, refreshToken);
    }

    public ResponseEntity<AuthenticationResponseDto> refreshToken(
            HttpServletRequest request,
            HttpServletResponse response) {

        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String token = authorizationHeader.substring(7);
        String username = jwtService.extractUsername(token);

        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("No user found"));

        if (jwtService.isValidRefresh(token, user)) {

            String accessToken = jwtService.generateAccessToken(user);
            String refreshToken = jwtService.generateRefreshToken(user);

            revokeAllToken(user);

            saveUserToken(accessToken, refreshToken, user);

            return new ResponseEntity<>(new AuthenticationResponseDto(accessToken, refreshToken), HttpStatus.OK);

        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}
