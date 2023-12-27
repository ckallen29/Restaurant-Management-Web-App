package com.example.demo.bootstrap;

import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.repositories.OutsourcedPartRepository;
import com.example.demo.repositories.PartRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.service.OutsourcedPartService;
import com.example.demo.service.OutsourcedPartServiceImpl;
import com.example.demo.service.ProductService;
import com.example.demo.service.ProductServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

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

    public BootStrapData(PartRepository partRepository, ProductRepository productRepository, OutsourcedPartRepository outsourcedPartRepository) {
        this.partRepository = partRepository;
        this.productRepository = productRepository;
        this.outsourcedPartRepository=outsourcedPartRepository;
    }

    @Override
    public void run(String... args) throws Exception {


        int partCount = (int) partRepository.count();
        if (partCount == 0) {
            /*
            // create a new instance of the OutsourcedPart class
            OutsourcedPart o= new OutsourcedPart();
            // set properties of the OutsourcedPart instance
            o.setCompanyName("Western Governors University");
            o.setName("out test");
            o.setInv(5);
            o.setPrice(20.0);
            o.setId(100L);
            //save the OutsourcedPart instance to the outsourcedPartRepository
            outsourcedPartRepository.save(o);
            //declare a variable to store the retrieved OutsourcedPart
            OutsourcedPart thePart=null;
            //retrieve all OutsourcedPart instances from the repository
            List<OutsourcedPart> outsourcedParts=(List<OutsourcedPart>) outsourcedPartRepository.findAll();
            //iterate over the list of the OutsourcedPart instances
            for(OutsourcedPart part:outsourcedParts){
                //check if the name property of the current part is equal to "out test"
                //if found, assign the current part to thePart
                if(part.getName().equals("out test"))thePart=part;
            }

            //print the companyName property of the retrieved OutsourcedPart
            System.out.println(thePart.getCompanyName());
            */


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
            List<OutsourcedPart> outsourcedParts=(List<OutsourcedPart>) outsourcedPartRepository.findAll();
            for(OutsourcedPart part:outsourcedParts){
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

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Products"+productRepository.count());
        System.out.println(productRepository.findAll());
        System.out.println("Number of Parts"+partRepository.count());
        System.out.println(partRepository.findAll());

    }
}
