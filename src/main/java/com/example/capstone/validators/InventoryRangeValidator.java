package com.example.capstone.validators;

import com.example.capstone.domain.Part;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class InventoryRangeValidator implements ConstraintValidator<ValidInventoryRange, Part> {

    public void initialize(ValidInventoryRange constraintAnnotation) {
    }

    public boolean isValid(Part part, ConstraintValidatorContext context) {
        if (part == null) {
            return false;
        }

        //check if minInv is less than or equal to maxInv
        if (part.getMinInv() != 0 && part.getMaxInv() != 0) {
            if (part.getMinInv() < part.getMaxInv()) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                        .addParameterNode(Integer.parseInt("minInv"))
                        .addConstraintViolation();
                return false;
            }
        }

        return true;
    }
}
