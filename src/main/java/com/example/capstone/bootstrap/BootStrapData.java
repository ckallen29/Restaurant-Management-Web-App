package com.example.capstone.bootstrap;

import com.example.capstone.domain.Order;
import com.example.capstone.domain.OutsourcedPart;
import com.example.capstone.domain.Product;
import com.example.capstone.domain.User;
import com.example.capstone.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 *
 *
 *
 */
@Component
public class BootStrapData implements CommandLineRunner {

    private final PartRepository partRepository;
    private final ProductRepository productRepository;
    private final OutsourcedPartRepository outsourcedPartRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final OrderRepository orderRepository;

    public BootStrapData(PartRepository partRepository,
                         ProductRepository productRepository,
                         OutsourcedPartRepository outsourcedPartRepository,
                         UserRepository userRepository,
                         PasswordEncoder passwordEncoder,
                         OrderRepository orderRepository) {
        this.partRepository = partRepository;
        this.productRepository = productRepository;
        this.outsourcedPartRepository=outsourcedPartRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.orderRepository = orderRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        int partCount = (int) partRepository.count();
        if (partCount == 0) {
            // creates and saves new OutsourcedPart instances to repo
            OutsourcedPart strawberries= new OutsourcedPart();
            strawberries.setCompanyName("Cane Farms");
            strawberries.setName("Strawberries");
            strawberries.setInv(5);
            strawberries.setMaxInv(20);
            strawberries.setMinInv(0);
            strawberries.setPrice(10.0);
            strawberries.setId(100L);
            outsourcedPartRepository.save(strawberries);

            OutsourcedPart sprinkles= new OutsourcedPart();
            sprinkles.setCompanyName("Deco Co.");
            sprinkles.setName("Sprinkles");
            sprinkles.setInv(5);
            sprinkles.setMaxInv(30);
            sprinkles.setMinInv(0);
            sprinkles.setPrice(2.0);
            sprinkles.setId(200L);
            outsourcedPartRepository.save(sprinkles);

            OutsourcedPart nuts= new OutsourcedPart();
            nuts.setCompanyName("Cane Farms");
            nuts.setName("Chopped Nuts");
            nuts.setInv(5);
            nuts.setMaxInv(20);
            nuts.setMinInv(0);
            nuts.setPrice(7.0);
            nuts.setId(300L);
            outsourcedPartRepository.save(nuts);

            OutsourcedPart cherries= new OutsourcedPart();
            cherries.setCompanyName("Cane Farms");
            cherries.setName("Cherries");
            cherries.setInv(5);
            cherries.setMaxInv(20);
            cherries.setMinInv(0);
            cherries.setPrice(10.0);
            cherries.setId(400L);
            outsourcedPartRepository.save(cherries);

            OutsourcedPart flowers= new OutsourcedPart();
            flowers.setCompanyName("Deco Co.");
            flowers.setName("Frosting Flowers");
            flowers.setInv(5);
            flowers.setMaxInv(30);
            flowers.setMinInv(0);
            flowers.setPrice(15.0);
            flowers.setId(400L);
            outsourcedPartRepository.save(flowers);

            OutsourcedPart partStrawberries = null;
            OutsourcedPart partSprinkles = null;
            OutsourcedPart partNuts = null;
            OutsourcedPart partCherries = null;
            OutsourcedPart partFlowers = null;

            // retrieves and prints all OutsourcedPart instances from repo
            List<OutsourcedPart> outsourcedParts=(List<OutsourcedPart>) outsourcedPartRepository.findAll();
            for(OutsourcedPart part : outsourcedParts){
                if(part.getName().equals("Strawberries")) {
                    partStrawberries = part;
                    System.out.println(partStrawberries.getCompanyName());
                }
                else if(part.getName().equals("Sprinkles")) {
                    partSprinkles = part;
                    System.out.println(partSprinkles.getCompanyName());
                }
                else if(part.getName().equals("Chopped Nuts")) {
                    partNuts = part;
                    System.out.println(partNuts.getCompanyName());
                }
                else if(part.getName().equals("Cherries")) {
                    partCherries = part;
                    System.out.println(partCherries.getCompanyName());
                }
                else if(part.getName().equals("Frosting Flowers")) {
                    partFlowers = part;
                    System.out.println(partFlowers.getCompanyName());
                }
            }
        }

        List<OutsourcedPart> outsourcedParts=(List<OutsourcedPart>) outsourcedPartRepository.findAll();
        for(OutsourcedPart part:outsourcedParts){
            System.out.println(part.getName()+" "+part.getCompanyName());
        }

        // creates and saves new Product instances to repo
        int productCount = (int) productRepository.count();
        if (productCount == 0) {
            Product round= new Product("Round Cake",25.0,15);
            Product sheet= new Product("Sheet Cake",20.0,15);
            Product cupcakes= new Product("Cupcakes, 12",25.0,15);
            Product cookies= new Product("Cookies, 12",15.0,15);
            Product brownies= new Product("Brownies, 6",20.0,15);
            productRepository.save(round);
            productRepository.save(sheet);
            productRepository.save(cupcakes);
            productRepository.save(cookies);
            productRepository.save(brownies);
        }

        int userCount = (int) userRepository.count();
        if (userCount == 0) {
            User testUser = new User();
            testUser.setUsername("user");
            testUser.setPassword(passwordEncoder.encode("password"));
            userRepository.save(testUser);
        }

        int orderCount = (int) orderRepository.count();
        if (orderCount == 0) {
            User testUser = userRepository.findByUsername("user");

            if (testUser != null) {
                LocalDateTime orderDate = LocalDateTime.of(2024, 7, 13, 0, 0);

                Order order1 = new Order(1L, testUser, orderDate, "ORD001");
                Order order2 = new Order(2L, testUser, orderDate, "ORD002");
                Order order3 = new Order(3L, testUser, orderDate, "ORD003");

                orderRepository.save(order1);
                orderRepository.save(order2);
                orderRepository.save(order3);
            } else {
                System.out.println("Some users are missing, orders cannot be created");
            }
        }

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Products"+productRepository.count());
        System.out.println(productRepository.findAll());
        System.out.println("Number of Parts"+partRepository.count());
        System.out.println(partRepository.findAll());

    }
}
