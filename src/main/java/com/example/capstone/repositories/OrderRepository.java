package com.example.capstone.repositories;

import com.example.capstone.domain.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order,Long> {

}
