package com.example.springbootecommerce.controller;

import com.example.springbootecommerce.dto.UserWithRoleAndCartDTO;
import com.example.springbootecommerce.model.Users;
import com.example.springbootecommerce.repository.UsersRepository;
import com.example.springbootecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UserService userService;

    @RequestMapping("/user/login")
    public ResponseEntity<?> getUserDetailsAfterLogin(Principal user) {
        Users users = usersRepository.findByEmailIgnoreCase(user.getName());
        if(!users.getEmailVerify())
        {
            Map<String,String> response=new HashMap<>();
            response.put("message","Your Email is not verified, Please verify your Email");
            return ResponseEntity.badRequest().body(response);
        }
        if (null!=users) {
            return ResponseEntity.ok().body(userService.userWithRoleAndCartDTO(users));
        }else {
            return null;
        }

    }

}
