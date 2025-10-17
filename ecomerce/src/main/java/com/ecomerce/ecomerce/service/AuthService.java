package com.ecomerce.ecomerce.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecomerce.ecomerce.dto.AuthResponse;
import com.ecomerce.ecomerce.dto.LoginRequest;
import com.ecomerce.ecomerce.dto.RegisterRequest;
import com.ecomerce.ecomerce.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import com.ecomerce.ecomerce.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
            .username(request.getUsername())
            .password(passwordEncoder.encode(request.getPassword()))
            .firstname(request.getFirstname())
            .lastname(request.getLastname())
            .role(request.getRole())
            .build();
        userRepository.save(user);
        return AuthResponse.builder()
            .token(jwtService.generatedToken(user))
            .build();
    }
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.generatedToken(user);
        return AuthResponse.builder()
            .token(token)
            .build();
    }
}
