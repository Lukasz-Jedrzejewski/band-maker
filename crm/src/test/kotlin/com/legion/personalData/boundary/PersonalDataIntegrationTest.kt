package com.legion.personalData.boundary

import com.fasterxml.jackson.databind.ObjectMapper
import com.legion.CrmApplication
import com.legion.personalData.control.PersonalDataRepository
import com.legion.tools.DatabaseCleaner
import com.legion.tools.objectFactories.getTestPersonalData
import com.legion.tools.objectFactories.getTestPersonalDataRequest
import com.legion.tools.objectFactories.getTestUser
import com.legion.user.control.UserRepository
import org.assertj.core.api.Assertions
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
class PersonalDataIntegrationTest {

    @Autowired
    lateinit var databaseCleaner: DatabaseCleaner

    @Autowired
    private lateinit var mvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Autowired
    private lateinit var personalDataRepository: PersonalDataRepository

    @Autowired
    private lateinit var userRepository: UserRepository

    private val user = getTestUser()
    private val personalData = getTestPersonalData(user = user)
    private val request = getTestPersonalDataRequest()

    @After
    fun tearDown() {
        databaseCleaner.clearH2()
    }

    @Test
    fun `should create new personal data`() {
        // given
        val savedUser = userRepository.save(user)
        val requestJson = objectMapper.writeValueAsString(request)

        // when
        mvc.perform(MockMvcRequestBuilders
                .post("/personal-data/${savedUser.id}", savedUser.id)
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(request.name))
                .andExpect(MockMvcResultMatchers.jsonPath("$.surname").value(request.surname))
                .andExpect(MockMvcResultMatchers.jsonPath("$.city").value(request.city))
                .andExpect(MockMvcResultMatchers.jsonPath("$.alias").value(request.alias))
                .andExpect(MockMvcResultMatchers.jsonPath("$.instrument").value(request.instrument.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.vocal").value(request.vocal.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(request.description))
                .andExpect(MockMvcResultMatchers.jsonPath("$.user.id").value(savedUser.id.toString()))
                .andExpect(MockMvcResultMatchers.status().isCreated)

        // then
        val result = personalDataRepository.findAll()
        Assertions.assertThat(result.size).isEqualTo(1)
        Assertions.assertThat(result[0].id).isNotNull
    }

    @Test
    fun `should update existing personal data`() {
        // given
        val savedUser = userRepository.save(user)
        val saved = personalDataRepository.save(personalData)
        val requestJson = objectMapper.writeValueAsString(request)

        // when
        mvc.perform(MockMvcRequestBuilders
                .post("/personal-data/${savedUser.id}", savedUser.id)
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(request.name))
                .andExpect(MockMvcResultMatchers.jsonPath("$.surname").value(request.surname))
                .andExpect(MockMvcResultMatchers.jsonPath("$.city").value(request.city))
                .andExpect(MockMvcResultMatchers.jsonPath("$.alias").value(request.alias))
                .andExpect(MockMvcResultMatchers.jsonPath("$.instrument").value(request.instrument.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.vocal").value(request.vocal.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(request.description))
                .andExpect(MockMvcResultMatchers.jsonPath("$.user.id").value(savedUser.id.toString()))
                .andExpect(MockMvcResultMatchers.status().isCreated)

        // then
        val result = personalDataRepository.findAll()
        Assertions.assertThat(result.size).isEqualTo(1)
        Assertions.assertThat(result[0].id).isNotNull
        Assertions.assertThat(result[0].name).isNotEqualTo(saved.name)
        Assertions.assertThat(result[0].surname).isNotEqualTo(saved.surname)
        Assertions.assertThat(result[0].city).isNotEqualTo(saved.city)
        Assertions.assertThat(result[0].alias).isNotEqualTo(saved.alias)
        Assertions.assertThat(result[0].instrument).isNotEqualTo(saved.instrument)
        Assertions.assertThat(result[0].vocal).isNotEqualTo(saved.vocal)
        Assertions.assertThat(result[0].description).isNotEqualTo(saved.description)
    }
}