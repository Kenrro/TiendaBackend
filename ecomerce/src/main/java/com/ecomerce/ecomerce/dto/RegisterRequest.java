package com.ecomerce.ecomerce.dto;

import com.ecomerce.ecomerce.role.UserRole;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
    String username;
    String password;
    String firstname;
    String lastname;
    @Enumerated(EnumType.STRING)
    UserRole role;
}
