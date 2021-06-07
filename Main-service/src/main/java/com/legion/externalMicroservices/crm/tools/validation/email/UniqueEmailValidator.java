package com.legion.externalMicroservices.crm.tools.validation.email;

import com.legion.externalMicroservices.crm.control.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmailConstraint, String> {

    @Autowired
    private UserService userService;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext ctx) {
        return !exists(email);
    }

    @Override
    public void initialize(UniqueEmailConstraint constraintAnnotation) {

    }

    private boolean exists(String email) {
        return userService.existsByEmail(email);
    }
}
