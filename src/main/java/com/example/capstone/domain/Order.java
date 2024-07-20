package com.example.capstone.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_order;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @Column(nullable = false)
    private LocalDateTime dateCreated;

    @Column(unique = true, nullable = false)
    private String orderNumber;

    public Order() {
    }

    public Order(Long id_order, User user, LocalDateTime dateCreated, String orderNumber) {
        this.id_order = id_order;
        this.user = user;
        this.dateCreated = dateCreated;
        this.orderNumber = orderNumber;
    }

    public Long getId() {
        return id_order;
    }

    public void setId(Long id_order) {
        this.id_order = this.id_order;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
}
