package com.example.shop.service;

import com.example.shop.model.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User registerNewUser(User user);
}
