package com.example.springbootecommerce.service;

import com.example.springbootecommerce.repository.EmailVerifyTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailVerifyTokenService {
    @Autowired
    private EmailVerifyTokenRepository emailVerifyTokenRepository;
}
