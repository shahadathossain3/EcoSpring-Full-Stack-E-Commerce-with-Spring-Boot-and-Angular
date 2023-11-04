package com.example.springbootecommerce.service;

import com.example.springbootecommerce.dto.*;
import com.example.springbootecommerce.model.Category;
import com.example.springbootecommerce.model.OrderItems;
import com.example.springbootecommerce.model.ProductOrder;
import com.example.springbootecommerce.repository.OrderItemsRepository;
import com.example.springbootecommerce.repository.ProductOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductOrderService {
    @Autowired
    private ProductOrderRepository productOrderRepository;

    @Autowired
    private OrderItemsRepository orderItemsRepository;

    public Map<String, Integer> pendingOrDeliveryCount()
    {
        Map<String,Integer> pendingOrDelivery=new HashMap<>();
        pendingOrDelivery.put("pendingQuantity",productOrderRepository.findAllByApproveStatusFalse().size());
        pendingOrDelivery.put("pendingDelivery",productOrderRepository.findAllByDeliveryStatusFalse().size());
        return pendingOrDelivery;
    }


    public List<ProductOrderWithOrderItemsDTO> pendingOrderList()
    {
        List<ProductOrder> productOrders = productOrderRepository.findAllByApproveStatusFalse();
        List<ProductOrderWithOrderItemsDTO> productOrderWithOrderItemsDTOS=new ArrayList<>();
        for(ProductOrder productOrder:productOrders)
        {
            productOrderWithOrderItemsDTOS.add(productOrderWithOrderItemsDTO(productOrder));
        }
        return productOrderWithOrderItemsDTOS;
    }

    public List<ProductOrderWithOrderItemsDTO> pendingDeliveryList()
    {
        List<ProductOrder> productOrders=productOrderRepository.findAllByDeliveryStatusFalse();
        List<ProductOrderWithOrderItemsDTO> productOrderWithOrderItemsDTOS=new ArrayList<>();
        for(ProductOrder productOrder:productOrders)
        {
            productOrderWithOrderItemsDTOS.add(productOrderWithOrderItemsDTO(productOrder));
        }
        return productOrderWithOrderItemsDTOS;
    }

    public ProductOrder addOrder(ProductOrder productOrder)
    {
        return productOrderRepository.save(productOrder);
    }

    public ProductOrder updateProduct(ProductOrder productOrder)
    {
        return productOrderRepository.save(productOrder);
    }

    public ProductOrderWithOrderItemsDTO productOrderWithOrderItemsDTO(ProductOrder productOrder)
    {
        ProductOrderWithOrderItemsDTO productOrderWithOrderItemsDTO=new ProductOrderWithOrderItemsDTO();
        UsersDTO usersDTO=new UsersDTO();
        usersDTO.setId(productOrder.getUsers().getId());
        usersDTO.setEmail(productOrder.getUsers().getEmail());
        usersDTO.setMobile(productOrder.getUsers().getMobile());
        usersDTO.setFirstName(productOrder.getUsers().getFirstName());
        usersDTO.setLastName(productOrder.getUsers().getFirstName());
        usersDTO.setPassword(null);

        productOrderWithOrderItemsDTO.setId(productOrder.getId());
        productOrderWithOrderItemsDTO.setAddress(productOrder.getAddress());
        productOrderWithOrderItemsDTO.setMobile(productOrder.getMobile());
        productOrderWithOrderItemsDTO.setAdminComment(productOrder.getAdminComment());
        productOrderWithOrderItemsDTO.setStatus(productOrder.getStatus());
        productOrderWithOrderItemsDTO.setCustomerComment(productOrder.getCustomerComment());
        productOrderWithOrderItemsDTO.setApproveStatus(productOrder.getApproveStatus());
        productOrderWithOrderItemsDTO.setDeliveryStatus(productOrder.getDeliveryStatus());
        productOrderWithOrderItemsDTO.setZipCode(productOrder.getZipCode());
        productOrderWithOrderItemsDTO.setFirstName(productOrder.getFirstName());
        productOrderWithOrderItemsDTO.setLastName(productOrder.getLastName());
        productOrderWithOrderItemsDTO.setTotalItems(productOrder.getTotalItems());
        productOrderWithOrderItemsDTO.setTotalPrice(productOrder.getTotalPrice());
        productOrderWithOrderItemsDTO.setUsers(usersDTO);


        List<OrderItemsProductDTO> orderItemsProductDTOS=new ArrayList<>();
        for(OrderItems orderItem: productOrder.getOrderItems())
        {
            OrderItemsProductDTO orderItemsProductDTO=new OrderItemsProductDTO();
            orderItemsProductDTO.setId(orderItem.getId());
            orderItemsProductDTO.setQuantity(orderItem.getQuantity());
            orderItemsProductDTO.setTotalItemsDiscount(orderItem.getTotalItemsDiscount());
            orderItemsProductDTO.setTotalItemsPrice(orderItem.getTotalItemsPrice());
            orderItemsProductDTO.setCreateDate(orderItem.getCreateDate());
            orderItemsProductDTO.setUpdateDate(orderItem.getUpdateDate());

            ProductWithCategoryListDTO productWithCategoryListDTO=new ProductWithCategoryListDTO();
            productWithCategoryListDTO.setId(orderItem.getProducts().getId());
            productWithCategoryListDTO.setTitle(orderItem.getProducts().getTitle());
            productWithCategoryListDTO.setPrice(orderItem.getProducts().getPrice());
            productWithCategoryListDTO.setDiscount(orderItem.getProducts().getDiscount());
            productWithCategoryListDTO.setBrand(orderItem.getProducts().getBrand());
            productWithCategoryListDTO.setDescription(orderItem.getProducts().getDescription());
            productWithCategoryListDTO.setImage(orderItem.getProducts().getImage());

            List<CatergotyDTO> catergotyDTOS=new ArrayList<>();
            for(Category category:orderItem.getProducts().getCategories())
            {
                CatergotyDTO catergotyDTO=new CatergotyDTO();
                catergotyDTO.setId(category.getId());
                catergotyDTO.setName(category.getName());
                catergotyDTO.setDescription(category.getDescription());
                catergotyDTOS.add(catergotyDTO);
            }
            productWithCategoryListDTO.setCategories(catergotyDTOS);
            orderItemsProductDTO.setProducts(productWithCategoryListDTO);
            orderItemsProductDTOS.add(orderItemsProductDTO);
        }
        productOrderWithOrderItemsDTO.setOrderItems(orderItemsProductDTOS);
        return productOrderWithOrderItemsDTO;

    }


    public List<ProductOrderWithOrderItemsDTO> productOrderList(Long id)
    {
        List<ProductOrder> productOrders =productOrderRepository.findAllByUsersId(id);
        List<ProductOrderWithOrderItemsDTO> productOrderWithOrderItemsDTOLis=new ArrayList<>();

        for(ProductOrder productOrder:productOrders)
        {
            productOrderWithOrderItemsDTOLis.add(productOrderWithOrderItemsDTO(productOrder));
        }
        return productOrderWithOrderItemsDTOLis;
    }


    public List<ProductOrderWithOrderItemsDTO> productOrderListPending(Long id)
    {
        List<ProductOrder> productOrders =productOrderRepository.findAllByUsersIdAndApproveStatusFalse(id);
        List<ProductOrderWithOrderItemsDTO> productOrderWithOrderItemsDTOLis=new ArrayList<>();

        for(ProductOrder productOrder:productOrders)
        {
//            List<OrderItems> orderItems=orderItemsRepository.findByProductOrderId(productOrder.getId());
//            productOrder.setOrderItems(orderItems);
            productOrderWithOrderItemsDTOLis.add(productOrderWithOrderItemsDTO(productOrder));
        }
        return productOrderWithOrderItemsDTOLis;
    }

    public ProductOrder getProductOrderById(Long id)
    {
        ProductOrder productOrder = productOrderRepository.findById(id).orElse(null);
        productOrder.setApproveStatus(true);
        productOrder.setDeliveryStatus(true);
        return productOrderRepository.save(productOrder);
    }


    public void deleteOrder(Long id)
    {
        productOrderRepository.deleteById(id);
    }

}
