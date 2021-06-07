package com.legion.user.model;

import com.legion.tools.validation.password.PasswordConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PasswordResetRequest {

    @PasswordConstraint
    private String newPassword;

    @PasswordConstraint
    private String confirmPassword;
}
