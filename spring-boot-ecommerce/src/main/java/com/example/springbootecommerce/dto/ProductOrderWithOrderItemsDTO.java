package com.example.springbootecommerce.dto;


import java.util.List;

public class ProductOrderWithOrderItemsDTO {

    private Long id;
    private UsersDTO users;
    private String status;
    private List<OrderItemsProductDTO> orderItems;
    private String mobile;
    private String firstName;
    private String lastName;
    private String address;
    private String zipCode;
    private String customerComment;
    private String adminComment;
    private Boolean approveStatus;
    private Boolean deliveryStatus;
    private Integer totalPrice;
    private Integer totalItems;

    public ProductOrderWithOrderItemsDTO() {
    }

    public ProductOrderWithOrderItemsDTO(Long id, UsersDTO users, String status, List<OrderItemsProductDTO> orderItems, String mobile, String firstName, String lastName, String address, String zipCode, String customerComment, String adminComment, Boolean approveStatus, Boolean deliveryStatus, Integer totalPrice, Integer totalItems) {
        this.id = id;
        this.users = users;
        this.status = status;
        this.orderItems = orderItems;
        this.mobile = mobile;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.zipCode = zipCode;
        this.customerComment = customerComment;
        this.adminComment = adminComment;
        this.approveStatus = approveStatus;
        this.deliveryStatus = deliveryStatus;
        this.totalPrice = totalPrice;
        this.totalItems = totalItems;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UsersDTO getUsers() {
        return users;
    }

    public void setUsers(UsersDTO users) {
        this.users = users;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderItemsProductDTO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemsProductDTO> orderItems) {
        this.orderItems = orderItems;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCustomerComment() {
        return customerComment;
    }

    public void setCustomerComment(String customerComment) {
        this.customerComment = customerComment;
    }

    public String getAdminComment() {
        return adminComment;
    }

    public void setAdminComment(String adminComment) {
        this.adminComment = adminComment;
    }

    public Boolean getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(Boolean approveStatus) {
        this.approveStatus = approveStatus;
    }

    public Boolean getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(Boolean deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
