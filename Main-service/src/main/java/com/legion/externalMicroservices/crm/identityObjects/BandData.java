package com.legion.externalMicroservices.crm.identityObjects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class BandData {
    private UUID id;
    private String name;
    private String city;
    private String phoneNumber;
    private String genre;
    private String description;
    private Instant createDate;
    private Instant updateDate;
    private boolean removed;
}