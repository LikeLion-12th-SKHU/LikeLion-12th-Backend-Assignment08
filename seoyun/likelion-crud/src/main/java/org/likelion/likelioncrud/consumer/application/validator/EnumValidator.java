package org.likelion.likelioncrud.consumer.application.validator;

import jakarta.validation.ConstraintValidatorContext;
import org.likelion.likelioncrud.consumer.domain.Part;
import jakarta.validation.ConstraintValidator;

public class EnumValidator implements ConstraintValidator<EnumValid, Part> {
    @Override
    public boolean isValid(Part part, ConstraintValidatorContext context) {
        if (part == null) {
            return true; // null 값은 다른 필수 조건에 의해 검증됨
        }

        return part == Part.WEB || part == Part.SERVER;
    }
}