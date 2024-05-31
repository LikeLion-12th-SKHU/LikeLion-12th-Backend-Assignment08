package org.likelion.likelionassignmentcrud.game.api.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.likelion.likelionassignmentcrud.game.domain.Platform;

public class EnumValidator implements ConstraintValidator<EnumValid, Platform> {
    @Override
    public boolean isValid(Platform platform, ConstraintValidatorContext context) {
        if (platform == null) {
            return true;
        }

        return platform == Platform.PC || platform == Platform.CONSOLE || platform == Platform.MOBILE;
    }
}
