package com.legion.user.control

import com.legion.tools.objectFactories.getTestRegisterRequest
import com.legion.tools.objectFactories.getTestUser
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

    @Test
    fun `should return true if user with given email already exist`() {
        // given
        whenever(userRepository.existsByEmail(any())).thenReturn(true)

        // when
        val result = userService.existsByEmail(user.email)

        // then
        verify(userRepository, times(1)).existsByEmail(any())
        assertThat(result).isTrue
    }

    @Test
    fun `should return false when user with given email does not exist`() {
        // given
        whenever(userRepository.existsByEmail(any())).thenReturn(false)

        // when
        val result = userService.existsByEmail("test@m.com")

        // then
        verify(userRepository, times(1)).existsByEmail(any())
        assertThat(result).isFalse
    }
}