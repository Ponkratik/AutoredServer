package com.ponkratov.autored.controller

import com.ponkratov.autored.AutoredApplication
import com.ponkratov.autored.dto.response.AdvertisementResponse
import com.ponkratov.autored.dto.response.MessageResponse
import com.ponkratov.autored.model.Advertisement
import com.ponkratov.autored.model.Car
import com.ponkratov.autored.model.CarFeatureList
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
import java.util.*
import kotlin.collections.ArrayList

@SpringBootTest(
    classes = [AutoredApplication::class],
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
internal class AdvertisementControllerTest {

    @Autowired
    private lateinit var restTemplate: TestRestTemplate

    private val baseUrl = "/api/advertisement"

    private val headers =
        HttpHeaders().apply { setBearerAuth("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwb25rcmF0b3Yud29ya0BnbWFpbC5jb20iLCJpYXQiOjE2NzY2NTM2MjEsImV4cCI6MTY3NjY2MTIyMX0.fIGUUQ7suAQAMMXZAIlGN3ze3W3R6HqNUzJWouRhK9JJL8CNTMSjtEW687sYCLmjDfeEjLuDm3diiMcuwmiviw") }

    @Test
    fun addAdvertisement() {
        val advertisement = Advertisement(
            userId = 11,
            location = "Брест",
            latitude = 52.095967,
            longitude = 23.682699,
            pricePerDay = 100.0,
            pricePerWeek = 500.0,
            pricePerMonth = 1200.0
        )
        val car = Car(
            vin = "WBA4J3C59JB711082",
            licensePlate = "6953AH-4",
            make = "BMW",
            model = "6 Serie Gran Coupe",
            manufacturedYear = Date(115, 7, 24),
            transmissionType = "Автоматическая",
            fuelType = "Бензин",
            doors = 4,
            seats = 5,
            carType = "Лифтбек",
            color = "Белый"
        )
        val carFeatures = CarFeatureList(
            isConditioner = true,
            isAllWheelDrive = true,
            isLeatherSeats = true,
            isHeatedSeats = true,
            isCooledSeats = true,
            isGps = true,
            isSkiRack = true,
            isAudioInput = true,
            isUsbInput = true,
            isBluetooth = true,
            isAndroidAuto = true,
            isAppleCarplay = true,
            isSunRoof = true
        )
        val bytes = ByteArray(10240)
        val testFile = object : ByteArrayResource(bytes) {
            override fun getFilename(): String = "test.jpg"
        }

        val body: MultiValueMap<String, Any> = LinkedMultiValueMap()
        body.add("car", car)
        body.add("advertisement", advertisement)
        body.add("car-features", carFeatures)
        body.add("files", testFile)
        body.add("files", testFile)
        body.add("files", testFile)

        val entity: HttpEntity<MultiValueMap<String, Any>> = HttpEntity(body, headers)
        val result = restTemplate
            .postForEntity("$baseUrl/add", entity, MessageResponse::class.java)

        assertNotNull(result)
        assertEquals(HttpStatus.OK, result?.statusCode)
        assertEquals(result?.body?.message, "Advertisement created successfully")
    }

    @Test
    fun verifyAdvertisement() {
        val entity: HttpEntity<MultiValueMap<String, Any>> = HttpEntity(headers)
        val result =
            restTemplate.exchange("$baseUrl/verify/6", HttpMethod.POST, entity, MessageResponse::class.java)

        assertNotNull(result)
        assertEquals(result?.body?.message, "Advertisement verified successfully")
        assertEquals(HttpStatus.OK, result?.statusCode)
    }

    @Test
    fun getAdvertisementsResponse() {
        val entity: HttpEntity<MultiValueMap<String, Any>> = HttpEntity(headers)
        val result = restTemplate
            .exchange("$baseUrl/get/full/all/7", HttpMethod.GET, entity, List::class.java)

        assertNotNull(result)
        assertEquals(HttpStatus.OK, result?.statusCode)
    }
}