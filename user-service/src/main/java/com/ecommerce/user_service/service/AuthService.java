package com.ecommerce.user_service.service;

import com.ecommerce.user_service.dto.RegisterRequest;
import com.ecommerce.user_service.entity.User;
import com.ecommerce.user_service.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.ecommerce.user_service.security.JwtService;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }
    // Handles new user registration
    public void register(RegisterRequest request) {

        User user = new User();

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());

        // PASSWORD ENCRYPTION
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setRole(request.getRole());

        // Persist user into database
        userRepository.save(user);
    }
    // Handles user login and returns JWT token if credentials are OK
    public String login(String username, String password) {
// Find user in DB
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            return "Invalid password";
        }
        // generate JWT token for auth user
        return jwtService.generateToken(user.getUsername());
    }

}