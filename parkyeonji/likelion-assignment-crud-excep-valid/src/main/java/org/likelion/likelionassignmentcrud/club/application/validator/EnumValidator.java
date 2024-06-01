package org.likelion.likelionassignmentcrud.club.application.validator;

import jakarta.validation.ConstraintValidator;
import org.likelion.likelionassignmentcrud.club.domain.Part;
import jakarta.validation.ConstraintValidatorContext;

public class EnumValidator implements ConstraintValidator<EnumValid, Part> {
    @Override
    public boolean isValid(Part part, ConstraintValidatorContext context) {
        if(part == null) {
            return true;
        }

        return part == Part.CENTRAL || part == Part.UNITED;
    }
}
