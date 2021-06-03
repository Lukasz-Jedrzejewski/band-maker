package com.legion.externalMicroservices.crm.control;

import com.legion.externalMicroservices.crm.CrmClient;
import com.legion.externalMicroservices.crm.identityObjects.RegisterRequest;
import com.legion.externalMicroservices.crm.identityObjects.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final CrmClient crmClient;

    public ResponseEntity<User> register(RegisterRequest registerRequest) {
        registerRequest.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        return crmClient.register(registerRequest);
    }
}
