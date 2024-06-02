package org.likelion.likelionrecrud.member.application.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = PartValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.RECORD_COMPONENT})
@Retention(RetentionPolicy.RUNTIME)
public @interface PartValid {
	String message() default "Backend나 Frontend로만 작성해주세요.";

	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	Class<? extends Enum<?>> enumClass();
	boolean allowLowerCase() default true; // 소문자 허용 여부
}
