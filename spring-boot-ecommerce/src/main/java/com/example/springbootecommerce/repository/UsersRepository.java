package com.example.springbootecommerce.repository;

import com.example.springbootecommerce.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users,Long> {
    //    EmailVerifyToken findByEmailVerifyTokenConfirmationToken(String token);
    Users findByEmailIgnoreCase(String email);
    Boolean existsByEmail(String email);


}
