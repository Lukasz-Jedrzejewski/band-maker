package com.legion.band.boundary

import com.fasterxml.jackson.databind.ObjectMapper
import com.legion.CrmApplication
import com.legion.band.control.BandDataRepository
import com.legion.tools.DatabaseCleaner
import com.legion.tools.objectFactories.getTestBandData
import com.legion.tools.objectFactories.getTestBandDataRequest
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
class BandDataIntegrationTest {

    @Autowired
    lateinit var databaseCleaner: DatabaseCleaner

    @Autowired
    private lateinit var mvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Autowired
    private lateinit var bandDataRepository: BandDataRepository

    @Autowired
    private lateinit var userRepository: UserRepository

    private val user = getTestUser()
    private val bandData = getTestBandData(user = user)
    private val request = getTestBandDataRequest()

    @After
    fun tearDown() {
        databaseCleaner.clearH2()
    }

    @Test
    fun `should create new band data`() {
        // given
        val savedUser = userRepository.save(user)
        val requestJson = objectMapper.writeValueAsString(request)

        // when
        mvc.perform(MockMvcRequestBuilders
                .post("/band-data/${savedUser.id}", savedUser.id)
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(request.name))
                .andExpect(MockMvcResultMatchers.jsonPath("$.city").value(request.city))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value(request.phoneNumber))
                .andExpect(MockMvcResultMatchers.jsonPath("$.genre").value(request.genre))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(request.description))
                .andExpect(MockMvcResultMatchers.jsonPath("$.user.id").value(savedUser.id.toString()))
                .andExpect(MockMvcResultMatchers.status().isCreated)

        // then
        val result = bandDataRepository.findAll()
        assertThat(result.size).isEqualTo(1)
        assertThat(result[0].id).isNotNull
    }

    @Test
    fun `should update existing band data`() {
        // given
        val savedUser = userRepository.save(user)
        val saved = bandDataRepository.save(bandData)
        val requestJson = objectMapper.writeValueAsString(request)

        // when
        mvc.perform(MockMvcRequestBuilders
                .post("/band-data/${savedUser.id}", savedUser.id)
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(request.name))
                .andExpect(MockMvcResultMatchers.jsonPath("$.city").value(request.city))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value(request.phoneNumber))
                .andExpect(MockMvcResultMatchers.jsonPath("$.genre").value(request.genre))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(request.description))
                .andExpect(MockMvcResultMatchers.jsonPath("$.user.id").value(savedUser.id.toString()))
                .andExpect(MockMvcResultMatchers.status().isCreated)

        // then
        val result = bandDataRepository.findAll()
        assertThat(result.size).isEqualTo(1)
        assertThat(result[0].id).isNotNull
        assertThat(result[0].city).isNotEqualTo(saved.city)
        assertThat(result[0].phoneNumber).isNotEqualTo(saved.phoneNumber)
        assertThat(result[0].genre).isNotEqualTo(saved.genre)
        assertThat(result[0].description).isNotEqualTo(saved.description)
    }
}