package com.legion

import com.legion.test.TestController
import com.legion.tools.objectFactories.getTestUser
import com.legion.user.boundary.UserController
import com.legion.user.control.UserRepository
import com.legion.user.control.UserService
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import io.restassured.module.mockmvc.RestAssuredMockMvc
import org.junit.jupiter.api.BeforeEach
import org.mockito.Mock
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(classes = [CrmApplication::class])
open class BaseClass {

    @Mock
    lateinit var userRepository: UserRepository

    lateinit var userService: UserService

    val user = getTestUser()

    @BeforeEach
    fun setUp() {
        userService = UserService(userRepository)
        whenever(userRepository.save(any())).thenReturn(user)
        whenever(userRepository.getById(any())).thenReturn(user)
        RestAssuredMockMvc.standaloneSetup(TestController(), UserController(userService))

    }
}