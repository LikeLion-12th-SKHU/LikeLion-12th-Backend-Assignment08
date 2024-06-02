package org.likelion.likelionassignmentcrud.food.application.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.likelion.likelionassignmentcrud.food.domain.Type;

public class EnumValidator implements ConstraintValidator<EnumValid, Type> {
    private Class<Type> enumClass;
    @Override
    public boolean isValid(Type type, ConstraintValidatorContext context) {
        if (type == null) {
            return true;
        }

        return type == type.KOREA || type == type.USA;
    }
}
