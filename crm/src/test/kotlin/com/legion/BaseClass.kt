package com.legion

import com.legion.band.boundary.BandDataController
import com.legion.band.control.BandDataRepository
import com.legion.band.control.BandDataService
import com.legion.test.TestController
import com.legion.tools.objectFactories.getTestBandData
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

    @Mock
    lateinit var bandDataRepository: BandDataRepository

    lateinit var userService: UserService
    lateinit var bandDataService: BandDataService

    private val user = getTestUser()
    private val bandData = getTestBandData(user = user)

    @BeforeEach
    fun setUp() {
        userService = UserService(userRepository)
        bandDataService = BandDataService(bandDataRepository, userService)

        whenever(userRepository.save(any())).thenReturn(user)
        whenever(userRepository.getById(any())).thenReturn(user)
        whenever(userRepository.existsByEmail(any())).thenReturn(true)

        whenever(bandDataRepository.existsByUserId(any())).thenReturn(false)
        whenever(bandDataRepository.findByUserId(any())).thenReturn(bandData)
        whenever(bandDataRepository.save(any())).thenReturn(bandData)

        RestAssuredMockMvc.standaloneSetup(TestController(),
                UserController(userService),
                BandDataController(bandDataService))
    }
}