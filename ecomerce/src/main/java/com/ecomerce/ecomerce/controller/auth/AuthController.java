package com.ecomerce.ecomerce.controller.auth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecomerce.ecomerce.dto.auth.AuthResponse;
import com.ecomerce.ecomerce.dto.auth.AuthUserResponse;
import com.ecomerce.ecomerce.dto.auth.LoginRequest;
import com.ecomerce.ecomerce.dto.auth.RegisterRequest;
import com.ecomerce.ecomerce.service.AuthService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.GetMapping;



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
    @GetMapping("check")
    public ResponseEntity<AuthUserResponse> verifyToken(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(authService.verifyToken(token));
    }
    
    
    
    
}
