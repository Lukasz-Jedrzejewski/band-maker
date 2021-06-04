package com.legion.user.boundary;

import com.legion.user.control.ExternalUserService;
import com.legion.externalMicroservices.crm.identityObjects.RegisterRequest;
import com.legion.externalMicroservices.crm.identityObjects.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/crm")
@AllArgsConstructor
public class ExternalUserController {

    private final ExternalUserService externalUserService;

    @PostMapping
    public ResponseEntity<User> register(@Valid @RequestBody RegisterRequest registerRequest) {
        return externalUserService.register(registerRequest);
    }
}
