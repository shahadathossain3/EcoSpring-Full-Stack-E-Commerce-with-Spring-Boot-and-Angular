package com.example.springbootecommerce.dto;

public class ProductDTO {
    private Long id;
    private String title;
    private Integer price;
    private Integer discount;
    private String brand;
    private String description;
    private byte[] image;

    public ProductDTO() {
    }

    public ProductDTO(Long id, String title, Integer price, Integer discount, String brand, String description, byte[] image) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.discount = discount;
        this.brand = brand;
        this.description = description;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
