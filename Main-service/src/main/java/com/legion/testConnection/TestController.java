package com.legion.testConnection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/test")
public class TestController {

    private final RestTemplate restTemplate;

    public TestController(RestTemplateBuilder restTemplate) {
        this.restTemplate = restTemplate.build();
    }

    @Value("${general.communication.crm.url}")
    private String url;

    private final String TEST_PATH = "/test";

    @GetMapping
    public ResponseEntity<String> makeConnection() {
        String fullPath = url + TEST_PATH;
        return restTemplate.getForEntity(fullPath, String.class);
    }
}
