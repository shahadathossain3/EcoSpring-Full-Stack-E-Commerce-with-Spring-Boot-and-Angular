package com.example.springbootecommerce.repository;

import com.example.springbootecommerce.model.EmailVerifyToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailVerifyTokenRepository extends JpaRepository<EmailVerifyToken, Long> {
    EmailVerifyToken findByConfirmationToken(String confirmationToken);
}
