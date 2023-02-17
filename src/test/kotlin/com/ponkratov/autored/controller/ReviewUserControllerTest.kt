package com.ponkratov.autored.controller

import com.ponkratov.autored.AutoredApplication
import com.ponkratov.autored.model.ReviewUser
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap


@SpringBootTest(classes = [AutoredApplication::class],
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class ReviewUserControllerTest {

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Test
    fun addReview_returnsOkResponse1() {
        val reviewUser = ReviewUser(mark = 5, comment = "Nice guy!", userFrom = 1, userTo = 7)
        val headers = HttpHeaders()
        headers.setBearerAuth("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwb25rcmF0b3Yud29ya0BnbWFpbC5jb20iLCJpYXQiOjE2NzY2NDU2NDAsImV4cCI6MTY3NjY1MzI0MH0.9hCIYQWb-oeuPHaRq6w7d6AKi3rFwiE8QKCVqVD1dDvgBfUJs16xnhqfru8LLNX-cu9aG6ZXUS8fF6Fp7fUTEQ")

        val body: MultiValueMap<String, Any> = LinkedMultiValueMap()
        body.add("review", reviewUser)

        val entity: HttpEntity<MultiValueMap<String, Any>> = HttpEntity(body, headers)
        val result = restTemplate
            .postForEntity("/api/review/user/add", entity, String::class.java)

        assertNotNull(result)
        assertEquals(HttpStatus.OK, result?.statusCode)
    }

    @Test
    fun deleteReviewById() {
    }
}