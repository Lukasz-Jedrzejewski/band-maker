package com.legion.externalMicroservices.crm.identityObjects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;

@NoArgsConstructor
@Getter
@Setter
public class RegisterRequest {
    @Email
    private String email;
    private String password;
    private UserType userType;
}
