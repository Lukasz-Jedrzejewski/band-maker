package com.legion

import com.legion.band.boundary.BandDataController
import com.legion.band.control.BandDataRepository
import com.legion.band.control.BandDataService
import com.legion.institutionData.boundary.InstitutionDataController
import com.legion.institutionData.control.InstitutionDataRepository
import com.legion.institutionData.control.InstitutionDataService
import com.legion.personalData.boundary.PersonalDataController
import com.legion.personalData.control.PersonalDataRepository
import com.legion.personalData.control.PersonalDataService
import com.legion.test.TestController
import com.legion.tools.objectFactories.getTestBandData
import com.legion.tools.objectFactories.getTestInstitutionData
import com.legion.tools.objectFactories.getTestPersonalData
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

    @Mock
    lateinit var personalDataRepository: PersonalDataRepository

    @Mock
    lateinit var institutionDataRepository: InstitutionDataRepository

    private lateinit var userService: UserService
    private lateinit var bandDataService: BandDataService
    private lateinit var personalDataService: PersonalDataService
    private lateinit var institutionDataService: InstitutionDataService

    private val user = getTestUser()
    private val bandData = getTestBandData(user = user)
    private val personalData = getTestPersonalData(user = user)
    private val institutionData = getTestInstitutionData(user = user)

    @BeforeEach
    fun setUp() {
        userService = UserService(userRepository)
        bandDataService = BandDataService(bandDataRepository, userService)
        personalDataService = PersonalDataService(personalDataRepository, userService)
        institutionDataService = InstitutionDataService(institutionDataRepository, userService)

        whenever(userRepository.save(any())).thenReturn(user)
        whenever(userRepository.getById(any())).thenReturn(user)
        whenever(userRepository.existsByEmail(any())).thenReturn(true)

        whenever(bandDataRepository.existsByUserId(any())).thenReturn(false)
        whenever(bandDataRepository.findByUserId(any())).thenReturn(bandData)
        whenever(bandDataRepository.save(any())).thenReturn(bandData)

        whenever(personalDataRepository.existsByUserId(any())).thenReturn(false)
        whenever(personalDataRepository.findByUserId(any())).thenReturn(personalData)
        whenever(personalDataRepository.save(any())).thenReturn(personalData)

        whenever(institutionDataRepository.existsByUserId(any())).thenReturn(false)
        whenever(institutionDataRepository.findByUserId(any())).thenReturn(institutionData)
        whenever(institutionDataRepository.save(any())).thenReturn(institutionData)

        RestAssuredMockMvc.standaloneSetup(TestController(),
                UserController(userService),
                BandDataController(bandDataService),
                PersonalDataController(personalDataService),
                InstitutionDataController(institutionDataService))
    }
}