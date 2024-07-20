package com.example.capstone.repositories;

import com.example.capstone.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);

    //Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);
}
