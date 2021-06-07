package com.legion.externalMicroservices.crm.identityObjects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class InstitutionData {
    private UUID id;
    private String name;
    private String city;
    private String street;
    private String streetNumber;
    private String localNumber;
    private String phoneNumber;
    private String description;
    private Instant createDate;
    private Instant updateDate;
    private boolean removed;
}