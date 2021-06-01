package com.legion.test;

import com.legion.externalMicroservices.crm.CrmClient;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@AllArgsConstructor
public class TestController {

    private final CrmClient crmClient;

    @GetMapping
    public ResponseEntity<String> test() {
        return new ResponseEntity<>(crmClient.makeConnection(), HttpStatus.OK);
    }
}
