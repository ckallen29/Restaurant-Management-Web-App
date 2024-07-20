package com.example.capstone.validators;

import com.example.capstone.domain.Part;
import com.example.capstone.domain.Product;
import com.example.capstone.service.ProductService;
import com.example.capstone.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 *
 *
 *
 */
public class EnufPartsValidator implements ConstraintValidator<ValidEnufParts, Product> {
    @Autowired
    private ApplicationContext context;
    public static  ApplicationContext myContext;
    @Override
    public void initialize(ValidEnufParts constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Product product, ConstraintValidatorContext constraintValidatorContext) {
        if(context==null) return true;
        if(context!=null)myContext=context;
        ProductService repo = myContext.getBean(ProductServiceImpl.class);
        if (product.getId() != 0) {
            Product myProduct = repo.findById((int) product.getId());
            for (Part p : myProduct.getParts()) {
                if (p.getInv()<(product.getInv()-myProduct.getInv()))return false;
                if (p.getInv() - 1 < p.getMinInv()) return false;
                if (p.getInv() + 1 > p.getMaxInv()) return false;
            }
            return true;
        }
        else{
                return true;
            }
    }
}