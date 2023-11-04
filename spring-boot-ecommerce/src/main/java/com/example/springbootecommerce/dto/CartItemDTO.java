package com.example.springbootecommerce.dto;

import java.util.Date;

public class CartItemDTO {
    private Long id;
    private ProductDTO product;
    private Long price;
    private Long discount;
    private Long quantity;
    private boolean active;
    private Date createDate;
    private Date updateDate;
    private String description;

    public CartItemDTO() {
    }

    public CartItemDTO(Long id, ProductDTO product, Long price, Long discount, Long quantity, boolean active, Date createDate, Date updateDate, String description) {
        this.id = id;
        this.product = product;
        this.price = price;
        this.discount = discount;
        this.quantity = quantity;
        this.active = active;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getDiscount() {
        return discount;
    }

    public void setDiscount(Long discount) {
        this.discount = discount;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
