package com.legion

import com.legion.test.TestController
import com.legion.user.boundary.UserController
import com.legion.user.control.UserService
import io.restassured.module.mockmvc.RestAssuredMockMvc
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(classes = [CrmApplication::class])
open class BaseClass {

    @Autowired
    lateinit var userService: UserService

    @BeforeEach
    fun setUp() {
        RestAssuredMockMvc.standaloneSetup(TestController(), UserController(userService))
    }
}