package com.example.springbootecommerce.dto;

import java.util.Date;


public class OrderItemsDTO {
    private Long id;
    private Integer totalItemsPrice;
    private Integer totalItemsDiscount;
    private Integer quantity;
    private Date createDate;
    private Date updateDate;

    public OrderItemsDTO() {
    }

    public OrderItemsDTO(Long id, Integer totalItemsPrice, Integer totalItemsDiscount, Integer quantity, Date createDate, Date updateDate) {
        this.id = id;
        this.totalItemsPrice = totalItemsPrice;
        this.totalItemsDiscount = totalItemsDiscount;
        this.quantity = quantity;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTotalItemsPrice() {
        return totalItemsPrice;
    }

    public void setTotalItemsPrice(Integer totalItemsPrice) {
        this.totalItemsPrice = totalItemsPrice;
    }

    public Integer getTotalItemsDiscount() {
        return totalItemsDiscount;
    }

    public void setTotalItemsDiscount(Integer totalItemsDiscount) {
        this.totalItemsDiscount = totalItemsDiscount;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
