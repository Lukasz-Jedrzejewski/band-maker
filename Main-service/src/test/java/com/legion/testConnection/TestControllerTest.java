package com.legion.testConnection;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static junit.framework.TestCase.assertEquals;

@SpringBootTest
@AutoConfigureStubRunner(ids = "com.legion:crm:+:stubs:8585", stubsMode = StubRunnerProperties.StubsMode.LOCAL)
@AutoConfigureWireMock(port = 8081)
class TestControllerTest {

    @Autowired
    TestController testController;

    @Test
    void makeConnection() {
        // given
        String response = "Hello crm and rest template";

        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/api/s2s/test"))
        .willReturn(WireMock.aResponse().withBody(response).withStatus(200)));

        // when
        ResponseEntity<String> entity = testController.makeConnection();

        // then
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertEquals(response, entity.getBody());
    }
}