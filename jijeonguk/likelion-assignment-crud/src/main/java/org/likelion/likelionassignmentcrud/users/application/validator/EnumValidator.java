package org.likelion.likelionassignmentcrud.users.application.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.likelion.likelionassignmentcrud.users.domain.PayOption;

public class EnumValidator implements ConstraintValidator<EnumValid, PayOption> {
    @Override
    public boolean isValid(PayOption payOption, ConstraintValidatorContext context){
        if(payOption ==null){
            return true;
        }
        return payOption == PayOption.KAKAO || payOption == PayOption.NAVER;
    }
}
