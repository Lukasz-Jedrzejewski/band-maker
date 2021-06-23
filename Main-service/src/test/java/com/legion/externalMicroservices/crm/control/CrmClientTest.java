package com.legion.externalMicroservices.crm.control;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.legion.externalMicroservices.crm.identityObjects.*;
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
    void should_check_if_user_with_given_email_already_exist() {
        // given
        String email = "randomemail@gmail.com";

        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/api/s2s/users?email="+email))
        .willReturn(WireMock.aResponse().withStatus(200).withBody(String.valueOf(Mockito.anyBoolean()))));

        // when
        crmClient.existsByEmail(email);
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
    void should_return_user_with_given_id() {
        // given
        UUID id = UUID.randomUUID();

        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/api/s2s/users/"+id))
        .willReturn(WireMock.aResponse().withStatus(200).withBody(Mockito.anyString())));

        // when
        crmClient.getById(id);
    }

    @Test
    void should_save_personal_data_for_given_user() {
        // given
        UUID id = UUID.randomUUID();
        PersonalDataRequest request = new PersonalDataRequest();
        request.setName(Mockito.anyString());
        request.setSurname(Mockito.anyString());
        request.setCity(Mockito.anyString());
        request.setAlias(Mockito.anyString());
        request.setInstrument(Mockito.anyString());
        request.setVocal(Mockito.anyBoolean());
        request.setDescription(Mockito.anyString());

        WireMock.stubFor(WireMock.post(WireMock.urlEqualTo("/api/s2s/personal-data/"+id))
                .willReturn(WireMock.aResponse().withStatus(201).withBody(Mockito.anyString())));

        // when
        crmClient.savePersonalData(id, request);
    }

    @Test
    void should_save_institution_data_for_given_user() {
        // given
        UUID id = UUID.randomUUID();
        InstitutionDataRequest request = new InstitutionDataRequest();
        request.setName(Mockito.anyString());
        request.setCity(Mockito.anyString());
        request.setStreet(Mockito.anyString());
        request.setStreetNumber(Mockito.anyString());
        request.setLocalNumber(Mockito.anyString());
        request.setPhoneNumber(Mockito.anyString());
        request.setDescription(Mockito.anyString());

        WireMock.stubFor(WireMock.post(WireMock.urlEqualTo("/api/s2s/institution-data/"+id))
                .willReturn(WireMock.aResponse().withStatus(201).withBody(Mockito.anyString())));

        // when
        crmClient.saveInstitutionData(id, request);
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