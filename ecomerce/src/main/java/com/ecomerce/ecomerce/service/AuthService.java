package com.ecomerce.ecomerce.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecomerce.ecomerce.dto.auth.AuthResponse;
import com.ecomerce.ecomerce.dto.auth.AuthUserResponse;
import com.ecomerce.ecomerce.dto.auth.LoginRequest;
import com.ecomerce.ecomerce.dto.auth.RegisterRequest;
import com.ecomerce.ecomerce.entity.Cart;
import com.ecomerce.ecomerce.entity.User;
import com.ecomerce.ecomerce.enums.UserError;
import com.ecomerce.ecomerce.exception.GeneralEcomerceException;
import com.ecomerce.ecomerce.repository.CartRepository;
import com.ecomerce.ecomerce.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final CartRepository cartRepository;
    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
            .username(request.getUsername())
            .password(passwordEncoder.encode(request.getPassword()))
            .firstname(request.getFirstname())
            .lastname(request.getLastname())
            .role(request.getRole())
            .build();
        try {
            userRepository.save(user);
            cartRepository.save(
                Cart.builder()
                    .user(user)
                    .build()
            );
        } catch (DataIntegrityViolationException e) {
            throw new GeneralEcomerceException(UserError.CONSTRAINS_VIOLATION, e);
        }  
        catch (Exception e) {
            throw new GeneralEcomerceException(UserError.ERROR_CREATING_USER);
        }
        return AuthResponse.builder()
            .token(jwtService.generatedToken(user))
            .build();
    }
    public AuthResponse login(LoginRequest request) {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch(BadCredentialsException e){
            throw new GeneralEcomerceException(UserError.BAD_CREDENTIALS);
        }
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.generatedToken(user);
        return AuthResponse.builder()
            .token(token)
            .build();
    }
    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new GeneralEcomerceException(UserError.BAD_CREDENTIALS);
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof UserDetails userDetails) {
            return userRepository.findByUsername(userDetails.getUsername())
                    .orElseThrow(() -> new GeneralEcomerceException(UserError.BAD_CREDENTIALS));
        }

        throw new GeneralEcomerceException(UserError.BAD_CREDENTIALS);
    }
    public AuthUserResponse verifyToken(String token) {
        try {
            if (token.startsWith(("Bearer "))) {
                token = token.substring(7);
            }

            String username = jwtService.getUsernameFromToken(token);
            if (username == null) {
                throw new GeneralEcomerceException((UserError.BAD_CREDENTIALS));
            }
            User user = userRepository.findByUsername(username)
                .orElseThrow(()-> new GeneralEcomerceException(UserError.BAD_CREDENTIALS));

            boolean isValid = jwtService.isTokenValid(token, user);
            if(!isValid) {
                throw new GeneralEcomerceException((UserError.BAD_CREDENTIALS));
            }
            return AuthUserResponse.builder()
                .username(user.getUsername())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .build();
        } catch (Exception e) {
            throw new GeneralEcomerceException((UserError.BAD_CREDENTIALS));
        }
    }
}
