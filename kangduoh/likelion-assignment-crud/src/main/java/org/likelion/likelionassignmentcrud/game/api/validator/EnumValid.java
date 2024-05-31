package org.likelion.likelionassignmentcrud.game.api.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = EnumValidator.class)
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
public @interface EnumValid {

    String message() default "PC, CONSOLE, MOBILE 중 하나로 작성해주세요.";
    Class<?>[] groups() default  { };
    Class<? extends Payload>[] payload() default { };
    Class<? extends java.lang.Enum<?>> enumClass();
}
