package com.example.springbootecommerce.controller;


import com.example.springbootecommerce.dto.OrderItemsProductDTO;
import com.example.springbootecommerce.dto.ProductOrderWithOrderItemsDTO;
import com.example.springbootecommerce.model.ProductOrder;
import com.example.springbootecommerce.model.OrderItems;
import com.example.springbootecommerce.model.Product;
import com.example.springbootecommerce.model.Users;
import com.example.springbootecommerce.repository.ProductOrderRepository;
import com.example.springbootecommerce.service.OrderItemsService;
import com.example.springbootecommerce.service.ProductOrderService;
import com.example.springbootecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("order/")
public class ProductOrderController {

    @Autowired
    private ProductOrderService productOrderService;

    @Autowired
    private OrderItemsService orderItemsService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductOrderRepository productOrderRepository;

    @GetMapping("pendingDeliveryCount")
    ResponseEntity<?> getPendingOrDeliveryCount()
    {
        return ResponseEntity.ok().body(productOrderService.pendingOrDeliveryCount());
    }


    @GetMapping("getAllPendingOrder")
    ResponseEntity<?> getAllPendingOrder()
    {
        return ResponseEntity.ok().body(productOrderService.pendingOrderList());
    }

    @GetMapping("getAllPendingDeliveryOrder")
    ResponseEntity<?> getAllPendingDelivery()
    {
        return ResponseEntity.ok().body(productOrderService.pendingDeliveryList());
    }

    @PostMapping("addOrder")
    ResponseEntity<?> addOrder(@RequestBody ProductOrderWithOrderItemsDTO productOrderWithOrderItemsDTO)
    {
        Users users = new Users();
        users.setId(productOrderWithOrderItemsDTO.getUsers().getId());
        ProductOrder productOrder =new ProductOrder();
        productOrder.setAddress(productOrderWithOrderItemsDTO.getAddress());
        productOrder.setAdminComment(productOrderWithOrderItemsDTO.getAdminComment());
        productOrder.setMobile(productOrderWithOrderItemsDTO.getMobile());
        productOrder.setApproveStatus(productOrderWithOrderItemsDTO.getApproveStatus());
        productOrder.setDeliveryStatus(productOrderWithOrderItemsDTO.getDeliveryStatus());
        productOrder.setCustomerComment(productOrderWithOrderItemsDTO.getCustomerComment());
        productOrder.setZipCode(productOrderWithOrderItemsDTO.getZipCode());
        productOrder.setTotalItems(productOrderWithOrderItemsDTO.getTotalItems());
        productOrder.setTotalPrice(productOrderWithOrderItemsDTO.getTotalPrice());
        productOrder.setFirstName(productOrderWithOrderItemsDTO.getFirstName());
        productOrder.setLastName(productOrderWithOrderItemsDTO.getLastName());
        productOrder.setApproveStatus(false);
        productOrder.setDeliveryStatus(false);
        productOrder.setUsers(users);
        ProductOrder responseProductOrder = productOrderService.addOrder(productOrder);
        List<OrderItems> orderItems=new ArrayList<>();
        for(OrderItemsProductDTO orderItemsProductDTO: productOrderWithOrderItemsDTO.getOrderItems())
        {
            OrderItems orderItem=new OrderItems();
            Product product=new Product();
            orderItem.setProductOrder(responseProductOrder);
            product.setId(orderItemsProductDTO.getProducts().getId());
            orderItem.setProducts(product);
            orderItems.add(orderItemsService.addOrderItems(orderItem));
        }
        responseProductOrder.setOrderItems(orderItems);
        return ResponseEntity.ok().build();
    }


    @PutMapping("orderUpdateById/{id}")
    ResponseEntity<?> orderUpdateById(@PathVariable Long id, @RequestBody ProductOrder productOrder)
    {
        productOrderService.getProductOrderById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("orderDeleteById/{id}")
    ResponseEntity<?> orderDeleteById(@PathVariable Long id)
    {
        productOrderService.deleteOrder(id);
        return ResponseEntity.ok().build();
    }

}
