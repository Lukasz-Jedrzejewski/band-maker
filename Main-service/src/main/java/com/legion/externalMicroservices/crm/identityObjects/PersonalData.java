package com.legion.externalMicroservices.crm.identityObjects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class PersonalData {
    private UUID id;
    private String name;
    private String surname;
    private String city;
    private String alias;
    private String instrument;
    private boolean vocal;
    private String description;
    private Instant createDate;
    private Instant updateDate;
    private boolean removed;
}