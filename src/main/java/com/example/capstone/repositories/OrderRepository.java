package com.example.capstone.repositories;

import com.example.capstone.domain.Order;
import com.example.capstone.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order,Long> {
    List<Order> findAllByOrderByUserUsernameAsc();

    Order findByOrderNumber(String orderNumber);

    boolean existsByOrderNumber(String orderNumber);

    List<Order> findByUserOrderByDateCreatedAsc(User user);
}
