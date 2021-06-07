package com.legion.externalMicroservices.crm.boundary;

import com.legion.externalMicroservices.crm.control.UserService;
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
public class CrmController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> register(@Valid @RequestBody RegisterRequest registerRequest) {
        return userService.register(registerRequest);
    }
}
