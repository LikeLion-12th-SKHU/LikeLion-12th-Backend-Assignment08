package org.likelion.assignmentvalid.product.application.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.likelion.assignmentvalid.product.domain.Part;

public class EnumValidator implements ConstraintValidator<EnumValid, Part> {
    @Override
    public boolean isValid(Part part, ConstraintValidatorContext context) {
        if (part == null)
            return true;

        return part == Part.FRUIT || part == Part.STUFF;
    }
}
