package com.legion.user.control;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.legion.externalMicroservices.crm.control.CrmClient;
import com.legion.externalMicroservices.crm.identityObjects.RegisterRequest;
import com.legion.externalMicroservices.crm.identityObjects.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ExternalUserService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final CrmClient crmClient;
    private final ObjectMapper mapper;

    public ResponseEntity<User> register(RegisterRequest registerRequest) {
        registerRequest.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        return crmClient.register(registerRequest);
    }

    public boolean existsByEmail(String email) {
        ResponseEntity<Boolean> result = crmClient.existsByEmail(email);
        return Boolean.parseBoolean(String.valueOf(result.getBody()));
    }
}
