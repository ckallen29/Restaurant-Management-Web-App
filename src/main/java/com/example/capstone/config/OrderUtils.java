package com.example.capstone.config;

import java.time.LocalDateTime;
import java.util.UUID;

public class OrderUtils {

    //generate unique order numbers
    public static String generateOrderNumber() {
        String uniqueID = UUID.randomUUID().toString();
        LocalDateTime now = LocalDateTime.now();
        String orderNumber = "ORD-" + now.toString() + "-" + uniqueID.substring(0, 8);
        return orderNumber;
    }
}
