package com.example.capstone.config;

import com.example.capstone.domain.User;
import com.example.capstone.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setUsername("user");
        user.setPassword(passwordEncoder.encode("password"));
        userRepository.save(user);
    }
}
