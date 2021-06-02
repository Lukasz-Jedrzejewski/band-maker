package com.legion.externalMicroservices.crm;

import com.legion.externalMicroservices.crm.identityObjects.RegisterRequest;
import com.legion.externalMicroservices.crm.identityObjects.User;
import com.legion.externalMicroservices.crm.tools.PathBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CrmClient extends PathBuilder {

    private final RestTemplate restTemplate;

    public CrmClient(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @Value("${general.communication.crm.url}")
    private String url;

    private final String REGISTER_PATH = "/users";

    public ResponseEntity<User> register(RegisterRequest registerRequest) {
        String uri = buildUri(buildUrl(url, REGISTER_PATH), null);

        return restTemplate.exchange(uri, HttpMethod.POST, new HttpEntity<>(registerRequest), User.class);
    }
}
