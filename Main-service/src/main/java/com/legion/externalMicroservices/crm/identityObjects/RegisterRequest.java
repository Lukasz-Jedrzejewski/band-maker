package com.legion.externalMicroservices.crm.identityObjects;

import com.legion.externalMicroservices.crm.tools.validation.email.EmailConstraint;
import com.legion.externalMicroservices.crm.tools.validation.password.PasswordConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RegisterRequest {
    @EmailConstraint
    private String email;

    @PasswordConstraint
    private String password;
    private UserType userType;
}
