package com.ponkratov.autored.controller

import com.ponkratov.autored.AutoredApplication
import com.ponkratov.autored.dto.request.RegisterRequest
import com.ponkratov.autored.dto.response.MessageResponse
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.core.io.ByteArrayResource
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.util.*


@SpringBootTest(
    classes = [AutoredApplication::class],
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
internal class UserControllerTest {

    @Autowired
    private lateinit var restTemplate: TestRestTemplate

    private val baseUrl = "/api/auth"

    private val headers = HttpHeaders().apply { setBearerAuth("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwb25rcmF0b3Yud29ya0BnbWFpbC5jb20iLCJpYXQiOjE2NzY2NTM2MjEsImV4cCI6MTY3NjY2MTIyMX0.fIGUUQ7suAQAMMXZAIlGN3ze3W3R6HqNUzJWouRhK9JJL8CNTMSjtEW687sYCLmjDfeEjLuDm3diiMcuwmiviw") }

    @Test
    fun registerUser_returnsOkResponse() {
        val userToRegister = RegisterRequest(
            fio = "Тест Тест Тест",
            email = "test3@gmail.com",
            rawPassword = "123456",
            phone = "+375298893413",
            birthdate = Date(95, 10, 5),
            passportNumber = "BM2493687",
            driverLicenseNumber = "2AB72138117",
            profileDescription = "Best dealer"
        )
        val bytes = ByteArray(10240)
        val testFile = object : ByteArrayResource(bytes) {
            override fun getFilename(): String = "test.jpg"
        }

        val body: MultiValueMap<String, Any> = LinkedMultiValueMap()
        body.add("registerRequest", userToRegister)
        body.add("avatar", testFile)
        body.add("passportPhoto", testFile)
        body.add("driverLicensePhoto", testFile)

        val entity: HttpEntity<MultiValueMap<String, Any>> = HttpEntity(body, headers)
        val result = restTemplate
            .postForEntity("$baseUrl/register", entity, MessageResponse::class.java)

        assertNotNull(result)
        assertEquals(HttpStatus.OK, result?.statusCode)
        assertEquals(result?.body?.message, "User was registered successfully")
    }

    @Test
    fun verifyUser_returnsOkResponse() {
        val entity: HttpEntity<MultiValueMap<String, Any>> = HttpEntity(headers)
        val result =
            restTemplate.exchange("$baseUrl/verify/19", HttpMethod.POST, entity, MessageResponse::class.java)

        assertNotNull(result)
        assertEquals(result?.body?.message, "User verified successfully")
        assertEquals(HttpStatus.OK, result?.statusCode)
    }
}