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
    lateinit var userRepository: UserRepository

    lateinit var userService: UserService

    private val user = getTestUser()
    private val request = getTestRegisterRequest()

    @Before
    fun setUp() {
        userService = UserService(userRepository)
    }

    @Test
    fun register() {
        // given
        whenever(userRepository.save(any())).thenReturn(user)

        // when
        val result = userService.register(request)

        // then
        verify(userRepository, times(1)).save(any())
        assertThat(result).isEqualTo(user)
    }


}