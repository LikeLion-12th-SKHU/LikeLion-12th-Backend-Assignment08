package org.likelion.likelionassignmentcrud.professor.application.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.likelion.likelionassignmentcrud.professor.domain.Department;

public class EnumValidator implements ConstraintValidator<EnumValid, Department> {
    @Override
    public boolean isValid(Department department, ConstraintValidatorContext context) {
        if (department == null) {
            return true;
        }
        return department == Department.SOFTWARE || department == Department.COMPUTER_SCIENCE;
        // 학과가 software인지 computerscience인지 검증
    }
}