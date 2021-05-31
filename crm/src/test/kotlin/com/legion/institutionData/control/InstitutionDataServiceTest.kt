package com.legion.institutionData.control

import com.legion.tools.objectFactories.getTestInstitutionData
import com.legion.tools.objectFactories.getTestInstitutionDataRequest
import com.legion.tools.objectFactories.getTestUser
import com.legion.user.control.UserRepository
import com.legion.user.control.UserService
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.assertj.core.api.Assertions
import org.junit.Before
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class InstitutionDataServiceTest {
    
    @Mock
    private lateinit var institutionDataRepository: InstitutionDataRepository

    @Mock
    private lateinit var userRepository: UserRepository
    
    private lateinit var institutionDataService: InstitutionDataService

    private lateinit var userService: UserService

    private val user = getTestUser()
    private val institutionData = getTestInstitutionData(user = user)
    private val institutionDataRequest = getTestInstitutionDataRequest()

    @Before
    fun setUp() {
        userService = UserService(userRepository)
        institutionDataService = InstitutionDataService(institutionDataRepository, userService)
    }

    @org.junit.Test
    fun `should create new institution data`() {
        // given
        whenever(institutionDataRepository.existsByUserId(any())).thenReturn(false)
        whenever(userRepository.getById(any())).thenReturn(user)
        whenever(institutionDataRepository.save(any())).thenReturn(institutionData)

        // when
        val result = institutionDataService.createOrUpdate(user.id, institutionDataRequest)

        // then
        verify(userRepository, times(1)).getById(any())
        verify(institutionDataRepository, times(1)).existsByUserId(any())
        verify(institutionDataRepository, times(1)).save(any())
        Assertions.assertThat(result).isEqualTo(institutionData)
    }

    @org.junit.Test
    fun `should update existing institution data`() {
        // given
        val updated = institutionData.copy(name = "updated", description = "updated description")
        whenever(institutionDataRepository.existsByUserId(any())).thenReturn(true)
        whenever(institutionDataRepository.findByUserId(any())).thenReturn(institutionData)
        whenever(institutionDataRepository.save(any())).thenReturn(updated)


        // when
        val result = institutionDataService.createOrUpdate(user.id, institutionDataRequest)

        // then
        verify(institutionDataRepository, times(1)).existsByUserId(any())
        verify(institutionDataRepository, times(1)).findByUserId(any())
        verify(institutionDataRepository, times(1)).save(any())
        Assertions.assertThat(result).isEqualTo(updated)
    }
}