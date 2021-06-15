package com.legion

import io.restassured.RestAssured
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest(classes = [CrmApplication::class])
open class BaseClass {

    @Value("\${server.port}")
    var port: Int = 0

    @BeforeEach
    fun setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
//        RestAssuredMockMvc.standaloneSetup(testController)
    }
}