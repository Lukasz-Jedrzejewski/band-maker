package com.legion.externalMicroservices.crm.identityObjects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class InstitutionDataRequest {
    String name;
    String city;
    String street;
    String streetNumber;
    String localNumber = null;
    String phoneNumber = null;
    String description;
}
