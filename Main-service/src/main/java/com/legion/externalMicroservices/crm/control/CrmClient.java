package com.legion.externalMicroservices.crm.control;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.legion.externalMicroservices.crm.identityObjects.PersonalData;
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

import java.util.List;
import java.util.UUID;

@Component
public class CrmClient extends PathBuilder {

    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;

    public CrmClient(RestTemplateBuilder builder, ObjectMapper mapper) {
        this.restTemplate = builder.build();
        this.mapper = mapper;
    }

    @Value("${general.communication.crm.url}")
    private String url;

    private final String USER_PATH = "/users";
    private final String PERSONAL_DATA_PATH = "/personal-data";

    private final String EMAIL_PARAM = "email";
    private final String NEW_PASSWORD_PARAM = "newPassword";

    public ResponseEntity<User> register(RegisterRequest registerRequest) {
        String uri = buildUri(buildUrl(url, USER_PATH), null);

        return restTemplate.exchange(uri, HttpMethod.POST, new HttpEntity<>(registerRequest), User.class);
    }

    public ResponseEntity<Boolean> existsByEmail(String email) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add(EMAIL_PARAM, email);
        String uri = buildUri(buildUrl(url, USER_PATH), params);

        return restTemplate.exchange(uri, HttpMethod.GET, null, Boolean.class);
    }

    public ResponseEntity<?> setPassword(String newPassword, UUID id) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add(NEW_PASSWORD_PARAM, newPassword);
        String uri = buildUri(buildUrl(url, USER_PATH+"/"+id), params);

        return restTemplate.exchange(uri, HttpMethod.PUT, null, User.class);
    }

    public ResponseEntity<?> savePersonalData(UUID id, Object object) {
        PersonalData personalData = mapper.convertValue(object, PersonalData.class);
        String uri = buildUri(buildUrl(url, PERSONAL_DATA_PATH+"/"+id), null);

        return restTemplate.exchange(uri, HttpMethod.POST, new HttpEntity<>(personalData), PersonalData.class);
    }

    public ResponseEntity<User> getById(UUID id) {
        String uri = buildUri(buildUrl(url, USER_PATH+"/"+id), null);

        return restTemplate.exchange(uri, HttpMethod.GET, null, User.class);
    }
}