package com.legion.externalMicroservices.crm.identityObjects;

import com.legion.tools.validation.email.EmailConstraint;
import com.legion.tools.validation.email.UniqueEmailConstraint;
import com.legion.tools.validation.password.PasswordConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RegisterRequest {

    @EmailConstraint
    @UniqueEmailConstraint
    private String email;

    @PasswordConstraint
    private String password;

    private UserType userType;
}
