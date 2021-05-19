package com.legion.externalMicroservices.crm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CrmClient {

    private final RestTemplate restTemplate;

    public CrmClient(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @Value("${general.communication.crm.url}")
    private String url;

    private final String CRM_PATH = "/test";

    public ResponseEntity<String> makeConnection() {
        String fullPath = url + CRM_PATH;
        return restTemplate.exchange(fullPath, HttpMethod.GET, null, String.class);
    }
}
