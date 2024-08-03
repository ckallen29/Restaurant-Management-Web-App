package com.example.capstone.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = InventoryRangeValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidInventoryRange {
    String message() default "Minimum inventory cannot be lower than maximum inventory";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
