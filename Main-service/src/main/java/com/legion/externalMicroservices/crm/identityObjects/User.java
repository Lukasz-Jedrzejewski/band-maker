package com.legion.externalMicroservices.crm.identityObjects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    private UUID id;
    private String email;
    private String userType;
    private String password;
    private Instant createDate;
    private Instant updateDate;
    private boolean removed;
}