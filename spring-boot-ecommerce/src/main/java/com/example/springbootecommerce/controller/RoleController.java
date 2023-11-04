package com.example.springbootecommerce.controller;

import com.example.springbootecommerce.model.Roles;
import com.example.springbootecommerce.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("role/")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("getAllRole")
    public ResponseEntity<?> getAllRole()
    {
        return ResponseEntity.ok().body(roleService.roleList());
    }

    @PostMapping("addRole")
    public ResponseEntity<?> addRole(@RequestBody Roles roles)
    {
        return ResponseEntity.ok().body(roleService.addRole(roles));
    }
}
