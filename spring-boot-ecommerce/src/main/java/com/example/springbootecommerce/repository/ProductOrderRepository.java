package com.example.springbootecommerce.repository;

import com.example.springbootecommerce.model.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {
    List<ProductOrder> findAllByUsersId(Long id);
    List<ProductOrder> findAllByApproveStatusFalse();
    List<ProductOrder> findAllByDeliveryStatusFalse();

    List<ProductOrder> findAllByUsersIdAndApproveStatusFalse(Long id);
}
