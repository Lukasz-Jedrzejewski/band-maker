package com.legion.institutionData.boundary

import com.fasterxml.jackson.databind.ObjectMapper
import com.legion.CrmApplication
import com.legion.institutionData.control.InstitutionDataRepository
import com.legion.tools.DatabaseCleaner
import com.legion.tools.objectFactories.getTestInstitutionData
import com.legion.tools.objectFactories.getTestInstitutionDataRequest
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
class InstitutionDataIntegrationTest {

    @Autowired
    lateinit var databaseCleaner: DatabaseCleaner

    @Autowired
    private lateinit var mvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Autowired
    private lateinit var institutionDataRepository: InstitutionDataRepository

    @Autowired
    private lateinit var userRepository: UserRepository

    private val user = getTestUser()
    private val institutionData = getTestInstitutionData(user = user)
    private val request = getTestInstitutionDataRequest()

    @After
    fun tearDown() {
        databaseCleaner.clearH2()
    }

    @Test
    fun `should create new institution data`() {
        // given
        val savedUser = userRepository.save(user)
        val requestJson = objectMapper.writeValueAsString(request)

        // when
        mvc.perform(MockMvcRequestBuilders
                .post("/institution-data/${savedUser.id}", savedUser.id)
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(request.name))
                .andExpect(MockMvcResultMatchers.jsonPath("$.city").value(request.city))
                .andExpect(MockMvcResultMatchers.jsonPath("$.street").value(request.street))
                .andExpect(MockMvcResultMatchers.jsonPath("$.streetNumber").value(request.streetNumber))
                .andExpect(MockMvcResultMatchers.jsonPath("$.localNumber").value(request.localNumber))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value(request.phoneNumber))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(request.description))
                .andExpect(MockMvcResultMatchers.jsonPath("$.user.id").value(savedUser.id.toString()))
                .andExpect(MockMvcResultMatchers.status().isCreated)

        // then
        val result = institutionDataRepository.findAll()
        Assertions.assertThat(result.size).isEqualTo(1)
        Assertions.assertThat(result[0].id).isNotNull
    }

    @Test
    fun `should update existing institution data`() {
        // given
        val savedUser = userRepository.save(user)
        val saved = institutionDataRepository.save(institutionData)
        val requestJson = objectMapper.writeValueAsString(request)

        // when
        mvc.perform(MockMvcRequestBuilders
                .post("/institution-data/${savedUser.id}", savedUser.id)
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(request.name))
                .andExpect(MockMvcResultMatchers.jsonPath("$.city").value(request.city))
                .andExpect(MockMvcResultMatchers.jsonPath("$.street").value(request.street))
                .andExpect(MockMvcResultMatchers.jsonPath("$.streetNumber").value(request.streetNumber))
                .andExpect(MockMvcResultMatchers.jsonPath("$.localNumber").value(request.localNumber))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value(request.phoneNumber))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(request.description))
                .andExpect(MockMvcResultMatchers.jsonPath("$.user.id").value(savedUser.id.toString()))
                .andExpect(MockMvcResultMatchers.status().isCreated)

        // then
        val result = institutionDataRepository.findAll()
        Assertions.assertThat(result.size).isEqualTo(1)
        Assertions.assertThat(result[0].id).isNotNull
        Assertions.assertThat(result[0].city).isNotEqualTo(saved.city)
        Assertions.assertThat(result[0].street).isNotEqualTo(saved.street)
        Assertions.assertThat(result[0].streetNumber).isNotEqualTo(saved.streetNumber)
        Assertions.assertThat(result[0].localNumber).isNotEqualTo(saved.localNumber)
        Assertions.assertThat(result[0].phoneNumber).isNotEqualTo(saved.phoneNumber)
        Assertions.assertThat(result[0].description).isNotEqualTo(saved.description)
    }
}