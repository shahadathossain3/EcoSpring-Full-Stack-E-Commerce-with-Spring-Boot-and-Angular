package com.example.springbootecommerce.model;

import com.example.springbootecommerce.dto.UsersDTO;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table
public class EmailVerifyToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String confirmationToken;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @OneToOne(targetEntity = Users.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable  = false, name="user_id")
    private Users users;

    public EmailVerifyToken() {
    }

    public EmailVerifyToken(Users users) {
        this.confirmationToken = UUID.randomUUID().toString();
        this.createDate = new Date();
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Users getUser() {
        return users;
    }

    public void setUser(UsersDTO usersDTO) {
        this.users = users;
    }
}
