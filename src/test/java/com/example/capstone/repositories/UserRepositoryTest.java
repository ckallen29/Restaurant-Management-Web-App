package com.example.capstone.repositories;

import com.example.capstone.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserRepositoryTest {
    UserRepository userRepository;
    @BeforeEach
    void setUp() { userRepository=mock(UserRepository.class); }

    @Test
    void findAll() {
        User user = new User();
        List userData = new ArrayList();
        userData.add(user);
        when(userRepository.findAll()).thenReturn(userData);
        List<User> users = (List<User>)userRepository.findAll();
        assertEquals(userData.size(),1);
    }
}
