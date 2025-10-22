package com.ecomerce.ecomerce.service;

import org.springframework.stereotype.Service;

import com.ecomerce.ecomerce.entity.User;
import com.ecomerce.ecomerce.enums.UserError;
import com.ecomerce.ecomerce.exception.GeneralEcomerceException;
import com.ecomerce.ecomerce.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    final UserRepository userRepository;
    public User getUserDetails(Long id){
        return userRepository.findById(id).orElseThrow(()-> new GeneralEcomerceException(UserError.BAD_CREDENTIALS));
    }
}
