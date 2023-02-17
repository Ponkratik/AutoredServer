package com.ponkratov.autored.controller

import com.ponkratov.autored.AutoredApplication
import com.ponkratov.autored.dto.response.MessageResponse
import com.ponkratov.autored.model.SupportRequest
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.core.io.ByteArrayResource
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap

@SpringBootTest(
    classes = [AutoredApplication::class],
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
internal class RideControllerTest {

    @Autowired
    private lateinit var restTemplate: TestRestTemplate

    private val baseUrl = "/api/ride"

    private val headers =
        HttpHeaders().apply { setBearerAuth("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwb25rcmF0b3Yud29ya0BnbWFpbC5jb20iLCJpYXQiOjE2NzY2NTM2MjEsImV4cCI6MTY3NjY2MTIyMX0.fIGUUQ7suAQAMMXZAIlGN3ze3W3R6HqNUzJWouRhK9JJL8CNTMSjtEW687sYCLmjDfeEjLuDm3diiMcuwmiviw") }

    @Test
    fun bookRide() {
        val body: MultiValueMap<String, Any> = LinkedMultiValueMap()
        body.add("advertisementId", 7)
        body.add("lessorId", 7)

        val entity: HttpEntity<MultiValueMap<String, Any>> = HttpEntity(body, headers)
        val result = restTemplate
            .postForEntity("$baseUrl/book/book", entity, MessageResponse::class.java)

        assertNotNull(result)
        assertEquals(HttpStatus.OK, result?.statusCode)
        assertEquals(result?.body?.message, "Book executed successfully")
    }

    @Test
    fun endRide() {
        val entity: HttpEntity<MultiValueMap<String, Any>> = HttpEntity(headers)
        val result =
            restTemplate.exchange("$baseUrl/book/end/14", HttpMethod.POST, entity, MessageResponse::class.java)

        assertNotNull(result)
        assertEquals(result?.body?.message, "Ride ended successfully")
        assertEquals(HttpStatus.OK, result?.statusCode)
    }

    @Test
    fun checkupCarBeforeRide() {
        val bytes = ByteArray(10240)
        val testFile = object : ByteArrayResource(bytes) {
            override fun getFilename(): String = "test.jpg"
        }

        val body: MultiValueMap<String, Any> = LinkedMultiValueMap()
        body.add("rideId", 21)
        body.add("files", testFile)
        body.add("files", testFile)
        body.add("files", testFile)
        body.add("files", testFile)

        val entity: HttpEntity<MultiValueMap<String, Any>> = HttpEntity(body, headers)
        val result = restTemplate
            .postForEntity("$baseUrl/checkup/before", entity, MessageResponse::class.java)

        assertNotNull(result)
        assertEquals(HttpStatus.OK, result?.statusCode)
        assertEquals(result?.body?.message, "Files uploaded successfully")
    }

    @Test
    fun signActByLessor() {
        val entity: HttpEntity<MultiValueMap<String, Any>> = HttpEntity(headers)
        val result =
            restTemplate.exchange("$baseUrl/sign/lessor/14", HttpMethod.POST, entity, MessageResponse::class.java)

        assertNotNull(result)
        assertEquals(result?.body?.message, "Act signed by lessor successfully")
        assertEquals(HttpStatus.OK, result?.statusCode)
    }

    @Test
    fun getRideDetails() {
        val entity: HttpEntity<MultiValueMap<String, Any>> = HttpEntity(headers)
        val result = restTemplate
            .exchange("$baseUrl/get/14", HttpMethod.GET, entity, SupportRequest::class.java)

        assertNotNull(result)
        assertEquals(HttpStatus.OK, result?.statusCode)
    }
}