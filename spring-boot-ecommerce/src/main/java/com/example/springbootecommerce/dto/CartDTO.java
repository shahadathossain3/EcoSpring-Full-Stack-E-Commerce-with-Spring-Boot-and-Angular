package com.example.springbootecommerce.dto;

import com.example.springbootecommerce.enumValue.CartEnum;
import com.example.springbootecommerce.model.Users;

public class CartDTO {
    private Long id;
    private Users users;
    private CartEnum name;

    public CartDTO() {
    }

    public CartDTO(Long id, Users users, CartEnum name) {
        this.id = id;
        this.users = users;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUser() {
        return users;
    }

    public void setUser(Users users) {
        this.users = users;
    }

    public CartEnum getName() {
        return name;
    }

    public void setName(CartEnum name) {
        this.name = name;
    }
}
