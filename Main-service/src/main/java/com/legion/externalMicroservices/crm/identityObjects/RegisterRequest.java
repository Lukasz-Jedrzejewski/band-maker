package com.legion.externalMicroservices.crm.identityObjects;

import com.legion.externalMicroservices.crm.tools.validation.PasswordConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
public class RegisterRequest {
    @Email
    @NotNull
    private String email;

    @PasswordConstraint
    private String password;
    private UserType userType;
}
