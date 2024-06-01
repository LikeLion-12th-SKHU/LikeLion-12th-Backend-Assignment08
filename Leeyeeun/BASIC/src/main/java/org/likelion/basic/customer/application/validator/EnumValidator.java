package org.likelion.basic.customer.application.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.likelion.basic.customer.domain.Part;

public class EnumValidator implements ConstraintValidator<EnumValid, Part> {

    @Override
    public boolean isValid(Part part, ConstraintValidatorContext context) {
        if (part == null) {
            return true;
        }

        return part == Part.VIP || part == Part.GENERAL;
    }
}
