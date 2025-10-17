package com.ecomerce.ecomerce.controller.auth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecomerce.ecomerce.dto.AuthResponse;
import com.ecomerce.ecomerce.dto.LoginRequest;
import com.ecomerce.ecomerce.dto.RegisterRequest;
import com.ecomerce.ecomerce.service.AuthService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    @PostMapping("register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest entity) {
        return ResponseEntity.ok(authService.register(entity));
    }
    @PostMapping("login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest entity) {
       return ResponseEntity.ok(authService.login(entity));
    }
    
    
}
