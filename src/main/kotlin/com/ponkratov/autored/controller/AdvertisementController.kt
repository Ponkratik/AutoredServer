package com.ponkratov.autored.controller

import com.ponkratov.autored.dto.response.AdvertisementResponse
import com.ponkratov.autored.dto.response.MessageResponse
import com.ponkratov.autored.model.Advertisement
import com.ponkratov.autored.model.Car
import com.ponkratov.autored.model.CarFeatureList
import com.ponkratov.autored.service.AdvertisementService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController
@RequestMapping("/api/advertisement")
class AdvertisementController {

    @Autowired
    private var _advertisementService: AdvertisementService? = null
    private val advertisementService get() = requireNotNull(_advertisementService)

    @PostMapping("/add")
    fun addAdvertisement(
        @RequestPart("advertisement") advertisement: Advertisement,
        @RequestPart("car") car: Car,
        @RequestPart("car-features") carFeatures: CarFeatureList,
        @RequestPart("files") files: List<MultipartFile>
    ): ResponseEntity<*> {
        val result = advertisementService.addAdvertisement(advertisement, car, carFeatures, files)
        return ResponseEntity.ok(MessageResponse(result))
    }

    @PostMapping("/verify/{id}")
    fun verifyAdvertisement(@PathVariable("id") id: Long): ResponseEntity<*> {
        val result = advertisementService.verifyAdvertisement(id)
        return ResponseEntity.ok(MessageResponse(result))
    }

    @GetMapping("/get/{id}")
    fun getAdvertisementById(@PathVariable("id") id: Long): ResponseEntity<Advertisement> {
        val result = advertisementService.getAdvertisement(id)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/get/all")
    fun getAdvertisements(): ResponseEntity<List<Advertisement>> {
        val result = advertisementService.getAdvertisements()
        return ResponseEntity.ok(result)
    }

    @GetMapping("/get/full/all")
    fun getAdvertisementsResponse(): ResponseEntity<List<AdvertisementResponse>> {
        val result = advertisementService.getAdvertisementsResponse()
        return ResponseEntity.ok(result)
    }

    @GetMapping("/get/full/{id}")
    fun getAdvertisementResponse(@PathVariable("id") advertisementId : Long): ResponseEntity<AdvertisementResponse> {
        val result = advertisementService.getAdvertisementResponse(advertisementId)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/get/full/all/{id}")
    fun getAdvertisementsResponseByUserId(@PathVariable("id") userId: Long): ResponseEntity<List<AdvertisementResponse>> {
        val result = advertisementService.getAdvertisementsResponseByUser(userId)
        return ResponseEntity.ok(result)
    }
}