package com.legion.externalMicroservices.crm.control;

import com.legion.externalMicroservices.crm.identityObjects.RegisterRequest;
import com.legion.externalMicroservices.crm.identityObjects.User;
import com.legion.tools.PathBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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

    private final String EMAIL_PARAM = "email";

    public ResponseEntity<User> register(RegisterRequest registerRequest) {
        String uri = buildUri(buildUrl(url, REGISTER_PATH), null);

        return restTemplate.exchange(uri, HttpMethod.POST, new HttpEntity<>(registerRequest), User.class);
    }

    public ResponseEntity<Boolean> existsByEmail(String email) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add(EMAIL_PARAM, email);
        String uri = buildUri(buildUrl(url, REGISTER_PATH), params);

        return restTemplate.exchange(uri, HttpMethod.GET, null, Boolean.class);
    }
}
