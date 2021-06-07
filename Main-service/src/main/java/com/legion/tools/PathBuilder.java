package com.legion.tools;

import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

public class PathBuilder {
    public String buildUri(String httpUrl, MultiValueMap<String, String> queryParams) {
        return UriComponentsBuilder.fromHttpUrl(httpUrl).queryParams(queryParams).toUriString();
    }

    public String buildUrl(String url, String path) {
        return UriComponentsBuilder.fromHttpUrl(url).path(path).toUriString();
    }
}
