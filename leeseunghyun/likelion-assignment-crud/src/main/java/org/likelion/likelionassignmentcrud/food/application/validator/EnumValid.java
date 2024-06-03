package org.likelion.likelionassignmentcrud.food.application.validator;

import jakarta.servlet.http.Part;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.likelion.likelionassignmentcrud.food.domain.Type;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = EnumValidator.class)
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
public @interface EnumValid {
    String message() default "KOREA나 USA으로 작성해주세요."; //type 은 양식과 한식을 나타냄

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    Class<Type> enumClass();
}
