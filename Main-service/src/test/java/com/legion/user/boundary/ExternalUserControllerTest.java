package com.legion.user.boundary;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.legion.externalMicroservices.crm.identityObjects.RegisterRequest;
import com.legion.externalMicroservices.crm.identityObjects.User;
import com.legion.externalMicroservices.crm.identityObjects.UserType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

import static junit.framework.Assert.*;
import static junit.framework.TestCase.assertEquals;

@SpringBootTest
@AutoConfigureStubRunner(ids = "com.legion:crm:+:stubs:8585", stubsMode = StubRunnerProperties.StubsMode.LOCAL)
@AutoConfigureWireMock(port = 8081)
class ExternalUserControllerTest {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ExternalUserController userController;

    @Test
    void register() throws Exception {
        // given
        RegisterRequest request = new RegisterRequest();
        request.setEmail("contractsamail@gmail.com");
        request.setPassword("HereShouldBeEncodedPassword");
        request.setUserType(UserType.MUSICIAN);

        User user = new User();
        user.setId(UUID.randomUUID());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setUserType(request.getUserType().toString());
        user.setCreateDate(Instant.now());
        user.setUpdateDate(Instant.now());
        user.setRemoved(false);

        String userJson = objectMapper.writeValueAsString(user);

        WireMock.stubFor(WireMock.post(WireMock.urlEqualTo("/api/s2s/users"))
                .willReturn(WireMock.aResponse().withStatus(201).withBody(userJson)));

        // when
        ResponseEntity<User> entity = userController.register(request);

        // then
//        assertEquals(HttpStatus.CREATED, entity.getStatusCode());
//        assertNotNull(entity.getBody().getId());
//        assertNotNull(entity.getBody().getCreateDate());
//        assertNotNull(entity.getBody().getUpdateDate());
//        assertEquals(request.getEmail(), entity.getBody().getEmail());
//        assertEquals(request.getPassword(), entity.getBody().getPassword());
//        assertEquals(request.getUserType(), entity.getBody().getUserType());
//        assertFalse(entity.getBody().isRemoved());
    }
}