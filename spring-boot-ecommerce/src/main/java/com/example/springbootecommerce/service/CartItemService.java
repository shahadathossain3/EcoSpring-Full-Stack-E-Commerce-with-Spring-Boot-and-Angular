package com.example.springbootecommerce.service;

import com.example.springbootecommerce.dto.CartItemDTO;
import com.example.springbootecommerce.dto.ProductDTO;
import com.example.springbootecommerce.model.CartItem;
import com.example.springbootecommerce.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    public List<CartItemDTO> getAllCartItemsList()
    {
        List<CartItem> cartItems=cartItemRepository.findAll();
        List<CartItemDTO> cartItemDTOS=new ArrayList<>();
        for(CartItem cartItem:cartItems)
        {
            ProductDTO productDTO=new ProductDTO();
            productDTO.setId(cartItem.getProduct().getId());
            productDTO.setTitle(cartItem.getProduct().getTitle());
            productDTO.setImage(cartItem.getProduct().getImage());
            productDTO.setDescription(cartItem.getProduct().getDescription());

            CartItemDTO cartItemDTO=new CartItemDTO();
            cartItemDTO.setId(cartItem.getId());
            cartItemDTO.setProduct(productDTO);
            cartItemDTO.setPrice(cartItem.getPrice());
            cartItemDTO.setDiscount(cartItem.getDiscount());
            cartItemDTO.setQuantity(cartItemDTO.getQuantity());
            cartItemDTO.setActive(cartItem.isActive());
            cartItemDTO.setCreateDate(cartItem.getCreateDate());
            cartItemDTO.setUpdateDate(cartItem.getUpdateDate());
            cartItemDTO.setDescription(cartItem.getDescription());

            cartItemDTOS.add(cartItemDTO);
        }

        return cartItemDTOS;
    }

    public List<CartItem> addCartItems(List<CartItem> cartItems)
    {
        return cartItemRepository.saveAll(cartItems);
    }
}
