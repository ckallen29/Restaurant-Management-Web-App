package com.example.capstone.controllers;

import com.example.capstone.domain.Part;
import com.example.capstone.domain.Product;
import com.example.capstone.repositories.ProductRepository;
import com.example.capstone.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

/**
 *
 */
@Controller
public class AddProductController {
    @Autowired
    private ApplicationContext context;
    @Autowired
    private ProductRepository productRepository;
    private PartService partService;
    private static Product product1;
    private Product product;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;

    @GetMapping("/showFormAddProduct")
    public String showFormAddPart(Model theModel) {
        Product product = new Product();
        theModel.addAttribute("product", product);

        List<Part> availParts = new ArrayList<>();
        for (Part p : partService.findAll()) {
            if (!product.getParts().contains(p)) availParts.add(p);
        }
        theModel.addAttribute("availparts", availParts);
        theModel.addAttribute("assparts", product.getParts());
        return "productForm";
    }

    @PostMapping("/showFormAddProduct")
    public String submitForm(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult, Model theModel) {
        //ensure parts and availability are always added to the model
        theModel.addAttribute("product", product);

        List<Part> availParts = new ArrayList<>();
        for (Part p : partService.findAll()) {
            availParts.add(p);
        }
        theModel.addAttribute("availparts", availParts);

        //if there are validation errors, return to the form view with errors
        if (bindingResult.hasErrors()) {
            //handle case for updating a product
            if (product.getId() != 0) {
                ProductService productService1 = context.getBean(ProductServiceImpl.class);
                Product product2 = productService1.findById((int) product.getId());
                theModel.addAttribute("assparts", product2.getParts());
                availParts.removeAll(product2.getParts());
            } else {
                //handle case for adding a new product
                theModel.addAttribute("assparts", new HashSet<>());
            }
            return "productForm";
        }

        //handle case for saving a product (new or existing)
        if (product.getId() != 0) {
            Product product2 = productService.findById((int) product.getId());
            if (product.getInv() - product2.getInv() > 0) {
                for (Part p : product2.getParts()) {
                    int inv = p.getInv();
                    p.setInv(inv - (product.getInv() - product2.getInv()));
                    partService.save(p);
                }
            }
        } else {
            //product.setInv(0);
        }
        productService.save(product);
        return "confirmationaddproduct";
    }


    @GetMapping("/showProductFormForUpdate")
    public String showProductFormForUpdate(@RequestParam("productID") int theId, Model theModel) {
        theModel.addAttribute("parts", partService.findAll());
        ProductService repo = context.getBean(ProductServiceImpl.class);
        Product theProduct = repo.findById(theId);
        product1 = theProduct;
        //    this.product=product;
        //set the employ as a model attibute to prepopulate the form
        theModel.addAttribute("product", theProduct);
        theModel.addAttribute("assparts", theProduct.getParts());
        List<Part> availParts = new ArrayList<>();
        for (Part p : partService.findAll()) {
            if (!theProduct.getParts().contains(p)) availParts.add(p);
        }
        theModel.addAttribute("availparts", availParts);
        //send over to our form
        return "productForm";
    }

    @GetMapping("/deleteproduct")
    public String deleteProduct(@RequestParam("productID") int theId, Model theModel) {
        ProductService productService = context.getBean(ProductServiceImpl.class);
        Product product2 = productService.findById(theId);
        for (Part part : product2.getParts()) {
            part.getProducts().remove(product2);
            partService.save(part);
        }
        product2.getParts().removeAll(product2.getParts());
        productService.save(product2);
        productService.deleteById(theId);

        return "confirmationdeleteproduct";
    }

    @GetMapping("/buyProduct")
    public String buyProduct(@RequestParam("productID") int theId, Model theModel) {
        ProductService productService = context.getBean(ProductServiceImpl.class);
        //productService.buyProduct(theId);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<Product> result = productRepository.findById((long) theId);

        if (result.isPresent()) {
            Product product = result.get();
            int currentInv = product.getInv();

            if (currentInv > 0) {
                try {
                    orderService.createOrder(username);
                    //reduce inv by 1
                    product.setInv(currentInv - 1);
                    productService.save(product);
                    return "confirmationbuyproduct";
                } catch (IllegalArgumentException e) {
                    theModel.addAttribute("error", e.getMessage());
                    return "errorbuyproduct";
                }
            } else {
                return "errorbuyproduct";
            }
        }

        return "confirmationbuyproduct";
        //return null;
    }

    public AddProductController(PartService partService) {
        this.partService = partService;
    }
// make the add and remove buttons work

    @GetMapping("/associatepart")
    public String associatePart(@Valid @RequestParam("partID") int theID, Model theModel) {
        //    theModel.addAttribute("product", product);
        //    Product product1=new Product();
        if (product1.getName() == null) {
            return "saveproductscreen";
        } else {
            product1.getParts().add(partService.findById(theID));
            partService.findById(theID).getProducts().add(product1);
            ProductService productService = context.getBean(ProductServiceImpl.class);
            productService.save(product1);
            partService.save(partService.findById(theID));
            theModel.addAttribute("product", product1);
            theModel.addAttribute("assparts", product1.getParts());
            List<Part> availParts = new ArrayList<>();
            for (Part p : partService.findAll()) {
                if (!product1.getParts().contains(p)) availParts.add(p);
            }
            theModel.addAttribute("availparts", availParts);
            return "productForm";
        }
        //        return "confirmationassocpart";
    }

    @GetMapping("/removepart")
    public String removePart(@RequestParam("partID") int theID, Model theModel) {
        theModel.addAttribute("product", product);
        //  Product product1=new Product();
        product1.getParts().remove(partService.findById(theID));
        partService.findById(theID).getProducts().remove(product1);
        ProductService productService = context.getBean(ProductServiceImpl.class);
        productService.save(product1);
        partService.save(partService.findById(theID));
        theModel.addAttribute("product", product1);
        theModel.addAttribute("assparts", product1.getParts());
        List<Part> availParts = new ArrayList<>();
        for (Part p : partService.findAll()) {
            if (!product1.getParts().contains(p)) availParts.add(p);
        }
        theModel.addAttribute("availparts", availParts);
        return "productForm";
    }
}
