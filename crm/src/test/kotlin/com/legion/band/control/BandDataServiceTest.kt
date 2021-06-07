package com.legion.band.control

import com.legion.user.control.UserRepository
import com.legion.user.control.UserService
import com.legion.tools.objectFactories.getTestBandData
import com.legion.tools.objectFactories.getTestBandDataRequest
import com.legion.tools.objectFactories.getTestUser
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.assertj.core.api.Assertions
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class BandDataServiceTest {

    @Mock
    private lateinit var bandDataRepository: BandDataRepository

    @Mock
    private lateinit var userRepository: UserRepository

    private lateinit var bandDataService: BandDataService

    private lateinit var userService: UserService

    private val user = getTestUser()
    private val bandData = getTestBandData(user = user)
    private val bandDataRequest = getTestBandDataRequest()

    @Before
    fun setUp() {
        userService = UserService(userRepository)
        bandDataService = BandDataService(bandDataRepository, userService)
    }

    @Test
    fun `should create new band data`() {
        // given
        whenever(bandDataRepository.existsByUserId(any())).thenReturn(false)
        whenever(userRepository.getById(any())).thenReturn(user)
        whenever(bandDataRepository.save(any())).thenReturn(bandData)

        // when
        val result = bandDataService.createOrUpdate(user.id, bandDataRequest)

        // then
        verify(userRepository, times(1)).getById(any())
        verify(bandDataRepository, times(1)).existsByUserId(any())
        verify(bandDataRepository, times(1)).save(any())
        Assertions.assertThat(result).isEqualTo(bandData)
    }

    @Test
    fun `should update existing band data`() {
        // given
        val updated = bandData.copy(name = "updated", description = "updated description")
        whenever(bandDataRepository.existsByUserId(any())).thenReturn(true)
        whenever(bandDataRepository.findByUserId(any())).thenReturn(bandData)
        whenever(bandDataRepository.save(any())).thenReturn(updated)


        // when
        val result = bandDataService.createOrUpdate(user.id, bandDataRequest)

        // then
        verify(bandDataRepository, times(1)).existsByUserId(any())
        verify(bandDataRepository, times(1)).findByUserId(any())
        verify(bandDataRepository, times(1)).save(any())
        Assertions.assertThat(result).isEqualTo(updated)
    }
}