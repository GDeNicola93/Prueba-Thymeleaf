package com.example.prueba_thymeleaf.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = CategoriaValidValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CategoriaValid {
    String message() default "La categoria seleccionada no es valida";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
