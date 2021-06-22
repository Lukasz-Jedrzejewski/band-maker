package com.legion.externalMicroservices.crm.control;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.legion.externalMicroservices.crm.identityObjects.BandDataRequest;
import com.legion.externalMicroservices.crm.identityObjects.RegisterRequest;
import com.legion.externalMicroservices.crm.identityObjects.UserType;
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
class CrmClientTest {

    @Autowired
    CrmClient crmClient;

    @Test
    void should_register_new_user() {
        // given
        RegisterRequest request = new RegisterRequest();
        request.setEmail(Mockito.anyString());
        request.setPassword(Mockito.anyString());
        request.setUserType(UserType.MUSICIAN);

        WireMock.stubFor(WireMock.post(WireMock.urlEqualTo("/api/s2s/users"))
                .willReturn(WireMock.aResponse().withStatus(201).withBody(Mockito.anyString())));

        // when
        crmClient.register(request);
    }

    @Test
    void existsByEmail() {
    }

    @Test
    void should_set_password_for_given_user() {
        // given
        UUID id = UUID.randomUUID();
        String request = "aaaaAAAA12@";

        WireMock.stubFor(WireMock.put(WireMock.urlEqualTo("/api/s2s/users/"+id+"?newPassword="+request))
                .willReturn(WireMock.aResponse().withStatus(201).withBody(Mockito.anyString())));

        // when
        crmClient.setPassword(request, id);
    }

    @Test
    void getById() {
    }

    @Test
    void savePersonalData() {
    }

    @Test
    void saveInstitutionData() {
    }

    @Test
    void should_save_band_data_for_given_user() {
        // given
        UUID id = UUID.randomUUID();
        BandDataRequest request = new BandDataRequest();
        request.setName(Mockito.anyString());
        request.setCity(Mockito.anyString());
        request.setPhoneNumber(Mockito.anyString());
        request.setGenre(Mockito.anyString());
        request.setCity(Mockito.anyString());

        WireMock.stubFor(WireMock.post(WireMock.urlEqualTo("/api/s2s/band-data/"+id))
                .willReturn(WireMock.aResponse().withStatus(201).withBody(Mockito.anyString())));

        // when
        crmClient.saveBandData(id, request);
    }
}