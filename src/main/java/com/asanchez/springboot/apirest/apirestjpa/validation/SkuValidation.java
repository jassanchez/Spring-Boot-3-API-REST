package com.asanchez.springboot.apirest.apirestjpa.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.asanchez.springboot.apirest.apirestjpa.services.IProductService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class SkuValidation implements ConstraintValidator<IsExistDb, String> {

    @Autowired
    private IProductService productService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(productService == null)
            return true;

        return !productService.existsBySku(value);
    }

}
