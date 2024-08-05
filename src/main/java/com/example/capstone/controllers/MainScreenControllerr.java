package com.example.capstone.controllers;

import com.example.capstone.domain.Part;
import com.example.capstone.domain.Product;
import com.example.capstone.service.CustomUserDetailsService;
import com.example.capstone.service.PartService;
import com.example.capstone.service.ProductService;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 *
 */

@Controller
public class MainScreenControllerr {
    private PartService partService;
    private ProductService productService;
    private CustomUserDetailsService customUserDetailsService;

    public MainScreenControllerr(PartService partService,
                                 ProductService productService,
                                 CustomUserDetailsService customUserDetailsService) {
        this.partService = partService;
        this.productService = productService;
        this.customUserDetailsService = customUserDetailsService;
    }

    @GetMapping("/mainscreen")
    public String listPartsandProducts(Model theModel, @Param("partkeyword") String partkeyword, @Param("productkeyword") String productkeyword) {
        //add to the sprig model
        List<Part> partList = partService.listAll(partkeyword);
        theModel.addAttribute("parts", partList);
        theModel.addAttribute("partkeyword", partkeyword);
        //theModel.addAttribute("products",productService.findAll());
        List<Product> productList = productService.listAll(productkeyword);
        theModel.addAttribute("products", productList);
        theModel.addAttribute("productkeyword", productkeyword);

        // display current user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        theModel.addAttribute("username", currentUsername);

        return "mainscreen";
    }

}
