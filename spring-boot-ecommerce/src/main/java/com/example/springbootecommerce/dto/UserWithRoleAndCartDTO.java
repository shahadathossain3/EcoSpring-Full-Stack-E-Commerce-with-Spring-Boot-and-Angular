package com.example.springbootecommerce.dto;

import java.util.List;

public class UserWithRoleAndCartDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String mobile;
    private byte[] image;
    private CartDTO cart;
    private List<RolesDTO> roles;

    public UserWithRoleAndCartDTO() {
    }

    public UserWithRoleAndCartDTO(Long id, String firstName, String lastName, String email, String password, String mobile, byte[] image, CartDTO cart, List<RolesDTO> roles) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.mobile = mobile;
        this.image = image;
        this.cart = cart;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public CartDTO getCart() {
        return cart;
    }

    public void setCart(CartDTO cart) {
        this.cart = cart;
    }

    public List<RolesDTO> getRoles() {
        return roles;
    }

    public void setRoles(List<RolesDTO> roles) {
        this.roles = roles;
    }
}
