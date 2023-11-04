package com.example.springbootecommerce.model;



import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product products;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_order_id")
    private ProductOrder productOrder;
    private Integer totalItemsPrice;
    private Integer totalItemsDiscount;
    private Integer quantity;
    private Date createDate;
    private Date updateDate;

    public OrderItems() {
    }

    public OrderItems(Long id, Product products, ProductOrder productOrder, Integer totalItemsPrice, Integer totalItemsDiscount, Integer quantity, Date createDate, Date updateDate) {
        this.id = id;
        this.products = products;
        this.productOrder = productOrder;
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

    public Product getProducts() {
        return products;
    }

    public void setProducts(Product product) {
        this.products = product;
    }

    public ProductOrder getProductOrder() {
        return productOrder;
    }

    public void setProductOrder(ProductOrder productOrder) {
        this.productOrder = productOrder;
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
