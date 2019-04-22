package com.scherbitsky.informsystemdept.controller

import com.google.gson.Gson
import com.scherbitsky.informsystemdept.dto.AdminDTO
import org.junit.Test

import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class MainControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun signUp() {

        /*val dto = AdminDTO()
        dto.userName = "admin2"
        dto.password = "admin2"

        val response = this.mockMvc.perform(post("/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Gson().toJson(dto)))
                .andDo(print())
                .andExpect(status().isCreated)
                .andReturn().response.contentAsString
        println("response = $response")*/
    }

    @Test
    fun login() {
    }
}