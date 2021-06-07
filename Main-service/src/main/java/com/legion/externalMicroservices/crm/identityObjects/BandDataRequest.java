package com.legion.externalMicroservices.crm.identityObjects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BandDataRequest {
    String name;
    String city;
    String phoneNumber = null;
    String genre;
    String description;
}
