package com.legion.user.control

import com.legion.user.entity.User
import com.legion.user.tools.objectFactories.getTestRegisterRequest
import com.legion.user.tools.objectFactories.getTestUser
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.assertj.core.api.Assertions.assertThat

@RunWith(MockitoJUnitRunner::class)
class UserServiceTest {

    @Mock
    private lateinit var userRepository: UserRepository

    private lateinit var userService: UserService

    private val user = getTestUser()
    private val request = getTestRegisterRequest()

    @Before
    fun setUp() {
        userService = UserService(userRepository)
    }

    @Test
    fun `should register new user`() {
        // given
        whenever(userRepository.save(any())).thenReturn(user)

        // when
        val result = userService.register(request)

        // then
        verify(userRepository, times(1)).save(any())
        assertThat(result).isEqualTo(user)
    }

    @Test
    fun `should return user with given id`() {
        // given
        whenever(userRepository.getById(any())).thenReturn(user)

        // when
        val result = userService.getById(user.id)

        // then
        verify(userRepository, times(1)).getById(any())
        assertThat(result).isEqualTo(user)
    }

    @Test
    fun `should change password for user with given id`() {
        // given
        val newPassword = "newTestPassword"
        val updated = user.copy(password = newPassword)
        whenever(userRepository.getById(any())).thenReturn(user)
        whenever(userRepository.save(any())).thenReturn(updated)

        // when
        val result = userService.changePassword(user.id, newPassword)

        // then
        verify(userRepository, times(1)).getById(any())
        verify(userRepository, times(1)).save(any())
        assertThat(result).isEqualTo(updated)
    }


}