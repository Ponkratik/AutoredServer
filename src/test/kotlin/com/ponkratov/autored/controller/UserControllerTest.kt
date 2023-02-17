package com.ponkratov.autored.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.ponkratov.autored.dto.request.RegisterRequest
import com.ponkratov.autored.model.User
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.mock.web.MockMultipartFile
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.util.*

@SpringBootTest
@AutoConfigureMockMvc
internal class UserControllerTest @Autowired constructor(
    val mockMvc: MockMvc,
    val objectMapper: ObjectMapper
) {

    private val baseUrl = "/api/auth"

    @Nested
    @DisplayName("POST /api/auth/register")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class RegisterUser {

        @Test
        fun `should register user`() {
            val newUser = RegisterRequest(
                fio = "Тест Тест Тест",
                email = "test@gmail.com",
                rawPassword = "123456",
                phone = "+375291111111",
                birthdate = Date(2001, 1, 10),
                passportNumber = "BM1234567",
                driverLicenseNumber = "AA1234567"
            )

            val testFile = MockMultipartFile(
                "file",
                "img.jpg",
                MediaType.IMAGE_JPEG_VALUE,
                byteArrayOf(10, 15, 15, 10)
            )

            /*val performPost = mockMvc.post("$baseUrl/register") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(newUser)

            }*/

            val performPost1 = mockMvc.perform(
                post("$baseUrl/register")
                    .param("registerRequest", objectMapper.writeValueAsString(newUser))
            )
                .andExpect(MockMvcResultMatchers.status().isOk)

            /*performPost
                .andDo { print() }
                .andExpect {
                    status { isCreated() }
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                        json(objectMapper.writeValueAsString(newUser))
                    }
                }*/
        }
    }

    /*@Test
    fun verifyUser() {

    }

    @Test
    fun getUserResponse() {
    }*/
}