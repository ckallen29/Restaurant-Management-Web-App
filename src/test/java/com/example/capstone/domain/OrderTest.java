package com.example.capstone.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class OrderTest {
    Order order;
    @Before
    public void setUp() throws Exception {
        order = new Order();
        //System.out.println("Order initialized: " + order);
    }

    @Test
    public void getId() {
        Long idValue = 1L;
        order.setId(idValue);
        assertEquals(order.getId(), idValue);
    }

    @Test
    public void getUser() {
        User user = new User();
        order.setUser(user);
        assertEquals(order.getUser(), user);
    }

    @Test
    public void getDateCreated() {
        LocalDateTime now = LocalDateTime.now();
        order.setDateCreated(now);
        assertEquals(order.getDateCreated(), now);
    }

    @Test
    public void getOrderNumber() {
        String orderNumber = "ORD1234";
        order.setOrderNumber(orderNumber);
        assertEquals(order.getOrderNumber(), orderNumber);
    }
}