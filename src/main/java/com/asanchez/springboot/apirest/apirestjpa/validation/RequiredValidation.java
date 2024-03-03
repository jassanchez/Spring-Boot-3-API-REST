package com.asanchez.springboot.apirest.apirestjpa.validation;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class RequiredValidation implements ConstraintValidator<IsRequiered, String>{

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        //return value != null && !value.isEmpty() && !value.isBlank();
        return StringUtils.hasText(value);
    }

}
