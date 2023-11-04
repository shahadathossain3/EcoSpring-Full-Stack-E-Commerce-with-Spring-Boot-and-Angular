package com.example.springbootecommerce.controller;

import com.example.springbootecommerce.model.CartItem;
import com.example.springbootecommerce.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cartitem/")
public class CartItemController {
    @Autowired
    private CartItemService cartItemService;


    @GetMapping("getAllCartItems")
    public ResponseEntity<?> getAllCartItems()
    {
        return ResponseEntity.ok().body(cartItemService.getAllCartItemsList());
    }


    @PostMapping("addcartitem")
    public ResponseEntity<?> addCartItem(@RequestBody List<CartItem> cartItem)
    {
        return ResponseEntity.ok().body(cartItemService.addCartItems(cartItem));
    }
}
