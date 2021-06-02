package com.legion.externalMicroservices.crm.identityObjects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RegisterRequest {

    private String email;
    private String password;
    private UserType userType;
}
