package com.legion.externalMicroservices.crm.identityObjects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PersonalDataRequest {
    String name;
    String surname;
    String city;
    String alias = null;
    String instrument = null;
    Boolean vocal;
    String description;
}
