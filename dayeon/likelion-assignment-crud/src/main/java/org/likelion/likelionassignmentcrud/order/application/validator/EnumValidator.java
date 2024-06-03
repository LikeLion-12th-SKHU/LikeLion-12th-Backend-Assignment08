package org.likelion.likelionassignmentcrud.order.application.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.likelion.likelionassignmentcrud.order.domain.Item;

public class EnumValidator implements ConstraintValidator<EnumValid, Item> {
    @Override
    public boolean isValid(Item item, ConstraintValidatorContext context) {
        if (item == null) {
            return true;
        }

        return item == Item.WATER || item == Item.OIL || item == Item.RICE;
    }
}
