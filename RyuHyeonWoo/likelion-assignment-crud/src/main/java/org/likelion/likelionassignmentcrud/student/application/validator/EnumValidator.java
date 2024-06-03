package org.likelion.likelionassignmentcrud.student.application.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.likelion.likelionassignmentcrud.student.domain.StudentGrade;

public class EnumValidator implements ConstraintValidator<EnumValid, StudentGrade> {
    @Override
    public boolean isValid(StudentGrade studentGrade, ConstraintValidatorContext context) {
        if (studentGrade == null) {
            return true; // null 값은 다른 필수 조건에 의해 검증됨
        }

        return studentGrade == studentGrade.FRESHMAN || studentGrade == studentGrade.SOPHOMORE || studentGrade == studentGrade.JUNIOR || studentGrade == studentGrade.SENIOR;
    }
}