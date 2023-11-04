package com.example.springbootecommerce.controller;

import com.example.springbootecommerce.model.Cart;
import com.example.springbootecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cart/")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("getAllCart")
    public ResponseEntity<?> getAllCart()
    {
        return ResponseEntity.ok().body(cartService.cartList());
    }

    @PostMapping("addCart")
    public ResponseEntity<?> addCart(@RequestBody Cart cart)
    {
        return ResponseEntity.ok().body(cartService.addCart(cart));
    }
}
