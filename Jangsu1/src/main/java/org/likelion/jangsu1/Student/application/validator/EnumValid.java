package org.likelion.jangsu1.Student.application.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

// 이 어노테이션이 Javadoc에 포함되도록 지정합니다.
@Documented
// 이 어노테이션의 지속시간을 런타임까지로 지정합니다.
@Retention(RUNTIME)
// 이 어노테이션을 검증하는 EnumValidator 클래스를 지정한다.
@Constraint(validatedBy = EnumValidator.class)
// 어노테이션의 적용 범위를 지정함.
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
public @interface EnumValid {
    String message() default "목록 중 하나를 지명하십시오. 목록 : FRONTEND, BACKEND, PM, QC";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
    Class<? extends java.lang.Enum<?>> enumClass();
}
