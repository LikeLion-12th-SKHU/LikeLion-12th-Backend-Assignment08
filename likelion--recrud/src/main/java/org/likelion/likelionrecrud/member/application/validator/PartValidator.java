package org.likelion.likelionrecrud.member.application.validator;

import org.likelion.likelionrecrud.exception.CustomException;
import org.likelion.likelionrecrud.exception.Error;
import org.likelion.likelionrecrud.member.domain.Part;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PartValidator implements ConstraintValidator<PartValid, String> {

	private Class<? extends Enum<?>> enumClass;
	private boolean allowLowerCase;

	public void initialize(PartValid constraintAnnotation){
		this.enumClass = constraintAnnotation.enumClass();
		this.allowLowerCase = constraintAnnotation.allowLowerCase();
	}
	@Override
	public boolean isValid(String part, ConstraintValidatorContext context) throws CustomException{
		if (part == null){	//null 들어가는건 에바니까 처리해줌.
			throw new CustomException(Error.VALIDATION_ERROR, "null 값은 들어갈 수 없습니다.");
		}
		Enum<?>[] enumConstants = enumClass.getEnumConstants();	//enum값들 싹 긁어오기.
		for (Enum<?> enumConstant : enumConstants){
			if (allowLowerCase && enumConstant.name().equalsIgnoreCase(part)){ //소문자 허용인데 + enum을 소문자로 바꿨을 때 일치하는게 있으면 통과
				return true;
			}
		}
		return false;
	}
}
