package com.example.springbootecommerce.model;

import com.example.springbootecommerce.enumValue.CartEnum;


import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "user_id")
    private Users users;
    @Enumerated(EnumType.STRING)
    private CartEnum name = CartEnum.SAVED;

    @OneToMany(mappedBy = "cart")
    private List<CartItem> cartItems;

    public Cart() {
    }

    public Cart(Long id, Users users, CartEnum name, List<CartItem> cartItems) {
        this.id = id;
        this.users = users;
        this.name = name;
        this.cartItems = cartItems;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public CartEnum getName() {
        return name;
    }

    public void setName(CartEnum name) {
        this.name = name;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
}
