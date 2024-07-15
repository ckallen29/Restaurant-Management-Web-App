package com.example.capstone.service;

import com.example.capstone.config.OrderUtils;
import com.example.capstone.domain.Order;
import com.example.capstone.domain.User;
import com.example.capstone.repositories.OrderRepository;
import com.example.capstone.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    public Order createOrder(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        Order order = new Order();
        order.setUser(user);
        order.setDateCreated(LocalDateTime.now());
        order.setOrderNumber(OrderUtils.generateOrderNumber());

        return orderRepository.save(order);
    }
}
