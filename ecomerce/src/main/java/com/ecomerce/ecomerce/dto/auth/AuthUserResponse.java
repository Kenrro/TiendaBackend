package com.ecomerce.ecomerce.dto.auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthUserResponse {
    private long id;
    private String firstname;
    private String lastname;
    private String username;
}
