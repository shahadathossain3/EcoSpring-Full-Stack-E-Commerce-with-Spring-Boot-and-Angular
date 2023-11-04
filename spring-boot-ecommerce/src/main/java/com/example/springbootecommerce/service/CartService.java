package com.example.springbootecommerce.service;

import com.example.springbootecommerce.model.Cart;
import com.example.springbootecommerce.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    public List<Cart> cartList()
    {
        return cartRepository.findAll();
    }

    public Cart addCart(Cart cart)
    {
        return cartRepository.save(cart);
    }
}
