package com.legion.user.boundary

import com.fasterxml.jackson.databind.ObjectMapper
import com.legion.CrmApplication
import com.legion.tools.DatabaseCleaner
import com.legion.tools.objectFactories.getTestRegisterRequest
import com.legion.tools.objectFactories.getTestUser
import com.legion.user.control.UserRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@EnableWebMvc
@RunWith(SpringRunner::class)
@SpringBootTest(classes = [CrmApplication::class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserIntegrationTest {

    @Autowired
    lateinit var databaseCleaner: DatabaseCleaner

    @Autowired
    private lateinit var mvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Autowired
    private lateinit var userRepository: UserRepository

    private val user = getTestUser()
    private val request = getTestRegisterRequest()

    @After
    fun tearDown() {
        databaseCleaner.clearH2()
    }

    @Test
    fun `should register and return new user`() {
        // given
        val requestJson = objectMapper.writeValueAsString(request)

        // when
        mvc.perform(MockMvcRequestBuilders
                .post("/users")
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(request.email))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userType").value(request.userType.toString()))
                .andExpect(MockMvcResultMatchers.status().isCreated)

        // then
        val result = userRepository.findAll()
        assertThat(result.size).isEqualTo(1)
        assertThat(result[0].id).isNotNull
        assertThat(result[0].email).isEqualTo(request.email)
    }

    @Test
    fun `should change password for user with given id`() {
        // given
        val saved = userRepository.save(user)
        val newPassword = "newTestPass"

        // when
        mvc.perform(MockMvcRequestBuilders
                .put("/users/${saved.id}", saved.id)
                .param("newPassword", newPassword)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(user.email))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password").value(newPassword))
                .andExpect(MockMvcResultMatchers.status().isOk)

        // then
        val result = userRepository.findAll()
        assertThat(result.size).isEqualTo(1)
        assertThat(result[0].id).isNotNull
        assertThat(result[0].email).isEqualTo(user.email)
        assertThat(result[0].password).isEqualTo(newPassword)
    }
}