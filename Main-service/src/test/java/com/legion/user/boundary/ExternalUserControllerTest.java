package com.legion.user.boundary;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.legion.externalMicroservices.crm.identityObjects.RegisterRequest;
import com.legion.externalMicroservices.crm.identityObjects.UserType;
import com.legion.user.model.PasswordResetRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;

import java.util.UUID;

@SpringBootTest
@AutoConfigureStubRunner(ids = "com.legion:crm:+:stubs:8585", stubsMode = StubRunnerProperties.StubsMode.LOCAL)
@AutoConfigureWireMock(port = 8081)
class ExternalUserControllerTest {

    @Autowired
    ExternalUserController userController;

    @Test
    void register() {
        // given
        RegisterRequest request = new RegisterRequest();
        request.setEmail(Mockito.anyString());
        request.setPassword(Mockito.anyString());
        request.setUserType(UserType.MUSICIAN);

        WireMock.stubFor(WireMock.post(WireMock.urlEqualTo("/api/s2s/users"))
                .willReturn(WireMock.aResponse().withStatus(201).withBody(Mockito.anyString())));

        // when
        userController.register(request);
    }

    @Test
    void setPassword() {
        // given
        UUID id = UUID.randomUUID();
        PasswordResetRequest request = new PasswordResetRequest("aaaaAAAA12@", "aaaaAAAA12@");

        WireMock.stubFor(WireMock.put(WireMock.urlEqualTo("/api/s2s/users/"+id+"?newPassword="+request.getNewPassword()))
                .willReturn(WireMock.aResponse().withStatus(201).withBody(Mockito.anyString())));

        // when
        userController.setPassword(id, request);
    }
}