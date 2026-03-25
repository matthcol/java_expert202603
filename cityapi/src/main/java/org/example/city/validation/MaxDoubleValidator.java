package org.example.city.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Objects;

public class MaxDoubleValidator implements ConstraintValidator<MaxDouble, Double> {

    private double maxValue;

    @Override
    public void initialize(MaxDouble constraintAnnotation) {
        maxValue = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(Double aDouble, ConstraintValidatorContext constraintValidatorContext) {
        return Objects.isNull(aDouble) || (aDouble <= maxValue);
    }
}
