package com.jolyvert.deliveryapp.bootstrap;

import com.jolyvert.deliveryapp.dto.RegistrationRequestDto;
import com.jolyvert.deliveryapp.model.User;
import com.jolyvert.deliveryapp.model.enums.Role;
import com.jolyvert.deliveryapp.repository.UserRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AdminSeeder implements ApplicationListener<ContextRefreshedEvent> {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    public AdminSeeder(
            UserRepository  userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        this.createAdministrator();
    }

    private void createAdministrator() {

        User user = new User();
        user.setEmail("admin@gmail.com");
        user.setTelephoneNumber("510 177 424");
        user.setPassword(passwordEncoder.encode("12345"));
        user.setRole(Role.ADMIN);

        Optional<User> optionalUser = userRepository.findByEmail("admin@gmail.com");

        if (optionalUser.isPresent()) {
            return;
        }

        userRepository.save(user);
    }
}
