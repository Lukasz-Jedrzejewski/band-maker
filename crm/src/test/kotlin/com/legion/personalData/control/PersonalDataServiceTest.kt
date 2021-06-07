package com.legion.personalData.control

import com.legion.tools.objectFactories.getTestPersonalData
import com.legion.tools.objectFactories.getTestPersonalDataRequest
import com.legion.tools.objectFactories.getTestUser
import com.legion.user.control.UserRepository
import com.legion.user.control.UserService
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.assertj.core.api.Assertions
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class PersonalDataServiceTest {
    
    @Mock
    private lateinit var personalDataRepository: PersonalDataRepository
    
    @Mock
    private lateinit var userRepository: UserRepository
    
    private lateinit var personalDataService: PersonalDataService
    
    private lateinit var userService: UserService

    private val user = getTestUser()
    private val personalData = getTestPersonalData(user = user)
    private val personalDataRequest = getTestPersonalDataRequest()

    @Before
    fun setUp() {
        userService = UserService(userRepository)
        personalDataService = PersonalDataService(personalDataRepository, userService)
    }

    @org.junit.Test
    fun `should create new band data`() {
        // given
        whenever(personalDataRepository.existsByUserId(any())).thenReturn(false)
        whenever(userRepository.getById(any())).thenReturn(user)
        whenever(personalDataRepository.save(any())).thenReturn(personalData)

        // when
        val result = personalDataService.createOrUpdate(user.id, personalDataRequest)

        // then
        verify(userRepository, times(1)).getById(any())
        verify(personalDataRepository, times(1)).existsByUserId(any())
        verify(personalDataRepository, times(1)).save(any())
        Assertions.assertThat(result).isEqualTo(personalData)
    }

    @org.junit.Test
    fun `should update existing band data`() {
        // given
        val updated = personalData.copy(name = "updated", description = "updated description")
        whenever(personalDataRepository.existsByUserId(any())).thenReturn(true)
        whenever(personalDataRepository.findByUserId(any())).thenReturn(personalData)
        whenever(personalDataRepository.save(any())).thenReturn(updated)


        // when
        val result = personalDataService.createOrUpdate(user.id, personalDataRequest)

        // then
        verify(personalDataRepository, times(1)).existsByUserId(any())
        verify(personalDataRepository, times(1)).findByUserId(any())
        verify(personalDataRepository, times(1)).save(any())
        Assertions.assertThat(result).isEqualTo(updated)
    }
}