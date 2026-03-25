package org.example.city.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.springframework.validation.annotation.Validated;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = MaxDoubleValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MaxDouble {
    double value();

    String message() default "La valeur dépasse le maximum autorisé";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
