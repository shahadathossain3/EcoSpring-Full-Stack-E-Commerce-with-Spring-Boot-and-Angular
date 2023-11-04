package com.example.springbootecommerce.model;



import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    private List<Users> users;

    public Roles() {
    }

    public Roles(Long id, String name, List<Users> users) {
        this.id = id;
        this.name = name;
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Users> getUser() {
        return users;
    }

    public void setUser(List<Users> users) {
        this.users = users;
    }
}
