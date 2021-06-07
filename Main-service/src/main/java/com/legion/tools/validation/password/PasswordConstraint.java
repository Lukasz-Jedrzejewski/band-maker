package com.legion.tools.validation.password;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordConstraint {
    String message() default "Invalid password. Must consist of at least eight characters," +
            " including lowercase letters, uppercase letters, numbers and special characters";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
