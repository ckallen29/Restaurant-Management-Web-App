package com.example.capstone;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@SpringBootApplication
public class DemoApplication {

    public static final String DB_USERNAME = System.getenv("DB_USERNAME");
    public static final String DB_PASSWORD = System.getenv("DB_PASSWORD");

    public static void main(String[] args) {
        System.out.println(DB_USERNAME);
        System.out.println(DB_PASSWORD);

        SpringApplication.run(DemoApplication.class, args);
    }

}
