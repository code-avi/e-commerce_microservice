package com.ecommerce.user_service.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtFilter;

    // injecting the custom jwt filter
    public SecurityConfig(JwtAuthenticationFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                // disable CSRF for simplicity (not recommended for production)
                .csrf(AbstractHttpConfigurer::disable)
                // Define which endpoints are public and which require auth
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()  // login and register endpoints
                        .requestMatchers("/h2-console/**").permitAll() // allow H2 console access
                        .anyRequest().authenticated()  // all other endpoints require auth
                )
                // require for H2 console to inside browser
                .headers(headers -> headers.frameOptions(frame -> frame.disable()));

        // Register JWT filter before auth
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    // pass encider user to hash user pass before save in DB
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}