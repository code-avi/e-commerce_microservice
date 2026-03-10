package com.ecommerce.user_service.controller;

import com.ecommerce.user_service.dto.LoginRequest;
import com.ecommerce.user_service.dto.RegisterRequest;
import com.ecommerce.user_service.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
// Base path for all auth releated API's
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register") // API for user registration
    public String register(@RequestBody RegisterRequest request) {

        authService.register(request);

        return "User registered successfully";
    }
    @PostMapping("/login") // Auth user and returns jwt token
    public String login(@RequestBody LoginRequest request) {

        return authService.login(
                request.getUsername(),
                request.getPassword()
        );
    }
    // for checking if the protected API is working
    // NOTE: This is under /auth/** which is permitAll, so it does NOT require a token.
    // Use /api/test (below) to verify JWT-protected access.
    @GetMapping("/test")
    public String test() {
        return "Auth endpoint working (public)";
    }
}