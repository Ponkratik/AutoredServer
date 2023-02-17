package com.ponkratov.autored.controller

import com.ponkratov.autored.AutoredApplication
import com.ponkratov.autored.dto.response.MessageResponse
import com.ponkratov.autored.model.ReviewUser
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
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
internal class ReviewUserControllerTest {

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Test
    fun addReview_returnsOkResponse() {
        val reviewUser = ReviewUser(mark = 5, comment = "Nice guy!", userFrom = 11, userTo = 15)
        val headers = HttpHeaders()
        headers.setBearerAuth("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwb25rcmF0b3Yud29ya0BnbWFpbC5jb20iLCJpYXQiOjE2NzY2NDU2NDAsImV4cCI6MTY3NjY1MzI0MH0.9hCIYQWb-oeuPHaRq6w7d6AKi3rFwiE8QKCVqVD1dDvgBfUJs16xnhqfru8LLNX-cu9aG6ZXUS8fF6Fp7fUTEQ")

        val body: MultiValueMap<String, Any> = LinkedMultiValueMap()
        body.add("review", reviewUser)

        val entity: HttpEntity<MultiValueMap<String, Any>> = HttpEntity(body, headers)
        val result = restTemplate
            .postForEntity("/api/review/user/add", entity, MessageResponse::class.java)

        assertNotNull(result)
        assertEquals(HttpStatus.OK, result?.statusCode)
        assertEquals(result?.body?.message, "Review added successfully")
    }

    @Test
    fun deleteReviewById_returnsOkResponse() {
        val headers = HttpHeaders()
        headers.setBearerAuth("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwb25rcmF0b3Yud29ya0BnbWFpbC5jb20iLCJpYXQiOjE2NzY2NDU2NDAsImV4cCI6MTY3NjY1MzI0MH0.9hCIYQWb-oeuPHaRq6w7d6AKi3rFwiE8QKCVqVD1dDvgBfUJs16xnhqfru8LLNX-cu9aG6ZXUS8fF6Fp7fUTEQ")

        val entity: HttpEntity<MultiValueMap<String, Any>> = HttpEntity(headers)
        val result =
            restTemplate.exchange("/api/review/user/delete/13", HttpMethod.DELETE, entity, MessageResponse::class.java)

        assertNotNull(result)
        assertEquals(result?.body?.message, "Review deleted successfully")
        assertEquals(HttpStatus.OK, result?.statusCode)
    }
}