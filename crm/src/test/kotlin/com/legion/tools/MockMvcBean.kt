package com.legion.tools

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@Component
class MockMvcBean {

    @Autowired
    lateinit var webApplicationContext: WebApplicationContext

    @Bean
    fun provideMockMvc(): MockMvc {
        return MockMvcBuilders.webAppContextSetup(webApplicationContext).build()
    }
}