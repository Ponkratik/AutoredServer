package com.ponkratov.autored.controller

import com.ponkratov.autored.AutoredApplication
import com.ponkratov.autored.model.SupportRequest
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.util.MultiValueMap

@SpringBootTest(
    classes = [AutoredApplication::class],
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
internal class StatisticControllerTest {

    @Autowired
    private lateinit var restTemplate: TestRestTemplate

    private val baseUrl = "/api/statistic"

    private val headers =
        HttpHeaders().apply { setBearerAuth("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwb25rcmF0b3Yud29ya0BnbWFpbC5jb20iLCJpYXQiOjE2NzY2NTM2MjEsImV4cCI6MTY3NjY2MTIyMX0.fIGUUQ7suAQAMMXZAIlGN3ze3W3R6HqNUzJWouRhK9JJL8CNTMSjtEW687sYCLmjDfeEjLuDm3diiMcuwmiviw") }

    @Test
    fun getStatistics() {
        val entity: HttpEntity<MultiValueMap<String, Any>> = HttpEntity(headers)
        val result = restTemplate
            .exchange("$baseUrl/get", HttpMethod.GET, entity, SupportRequest::class.java)

        assertNotNull(result)
        assertEquals(HttpStatus.OK, result?.statusCode)
    }
}