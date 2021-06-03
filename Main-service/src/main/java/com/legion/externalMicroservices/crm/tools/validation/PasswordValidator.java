package com.legion.externalMicroservices.crm.tools.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordConstraint, String> {

    private final String pattern = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";

    @Override
    public boolean isValid(String password, ConstraintValidatorContext ctx) {
        return password.matches(pattern);
    }

    @Override
    public void initialize(PasswordConstraint constraintAnnotation) {

    }
}
