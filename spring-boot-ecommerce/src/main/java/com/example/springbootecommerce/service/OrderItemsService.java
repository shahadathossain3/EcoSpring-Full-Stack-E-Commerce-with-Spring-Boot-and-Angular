package com.example.springbootecommerce.service;


import com.example.springbootecommerce.model.OrderItems;
import com.example.springbootecommerce.repository.OrderItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemsService {
    @Autowired
    private OrderItemsRepository orderItemsRepository;

    public OrderItems addOrderItems(OrderItems orderItems)
    {
        return orderItemsRepository.save(orderItems);
    }
}
