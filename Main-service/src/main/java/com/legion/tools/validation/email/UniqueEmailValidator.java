package com.legion.tools.validation.email;

import com.legion.user.control.ExternalUserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmailConstraint, String> {

    @Autowired
    private ExternalUserService externalUserService;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext ctx) {
        return !exists(email);
    }

    @Override
    public void initialize(UniqueEmailConstraint constraintAnnotation) {

    }

    private boolean exists(String email) {
        return externalUserService.existsByEmail(email);
    }
}
