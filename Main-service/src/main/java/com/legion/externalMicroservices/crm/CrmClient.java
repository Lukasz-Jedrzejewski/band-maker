package com.legion.externalMicroservices.crm;

import com.legion.externalMicroservices.crm.tools.PathBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class CrmClient extends PathBuilder {

    private final RestTemplate restTemplate;

    public CrmClient(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @Value("${general.communication.crm.url}")
    private String url;

    private final String CRM_PATH = "/test";

    public String makeConnection() {
        String uri = buildUri(buildUrl(url, CRM_PATH), null);

        return restTemplate.getForObject(uri, String.class);
    }
}
