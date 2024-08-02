package com.example.capstone;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        Properties properties = new Properties();
        try (InputStream inputStream = DemoApplication.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (inputStream == null) {
                System.out.println("Unable to find config.properties");
                return;
            }
            //load properties file
            properties.load(inputStream);
            //set properties as system properties
            System.setProperty("DB_USERNAME", properties.getProperty("DB_USERNAME"));
            System.setProperty("DB_PASSWORD", properties.getProperty("DB_PASSWORD"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        SpringApplication.run(DemoApplication.class, args);
    }

}
