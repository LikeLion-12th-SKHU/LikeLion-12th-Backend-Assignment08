package org.likelion.jangsu1.Student.application.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.likelion.jangsu1.Student.domain.Part;

public class EnumValidator implements ConstraintValidator<EnumValid, Part> {
    @Override
    public boolean isValid(Part part, ConstraintValidatorContext context) {
        if (part == null) {
            return true;
        }
        return part == Part.FRONTEND || part == Part.BACKEND || part == Part.QC || part == Part.PM;
    }

}
