package com.ponkratov.autored.controller

import com.ponkratov.autored.model.AttachmentTypeEnum
import com.ponkratov.autored.model.Ride
import com.ponkratov.autored.service.RideService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.util.*

@RestController
@RequestMapping("/api/ride")
class RideController {

    @Autowired
    private var _rideService: RideService? = null
    private val rideService get() = requireNotNull(_rideService)

    @PostMapping("/book/start")
    fun bookRide(
        @RequestPart("advertisementId") advertisementId: Long,
        @RequestPart("lessorId") lessorId: Long
    ): ResponseEntity<*> {
        val result = rideService.startBooking(
            advertisementId,
            lessorId,
            Date()
        )
        return ResponseEntity.ok(result)
    }

    @PostMapping("/book/end")
    fun endRide(
        @RequestPart("rideId") rideId: Long
    ): ResponseEntity<*> {
        val result = rideService.endRide(
            rideId,
            Date()
        )
        return ResponseEntity.ok(result)
    }

    @PostMapping("/checkup/before")
    fun checkupCarBeforeRide(
        @RequestPart("advertisementId") advertisementId: Long,
        @RequestPart("files") files: List<MultipartFile>
    ): ResponseEntity<*> {
        val result = rideService.checkupCar(
            advertisementId, files, AttachmentTypeEnum.TYPE_CAR_CHECK_PHOTO_BEFORE
        )

        return ResponseEntity.ok(result)
    }

    @PostMapping("/checkup/after")
    fun checkupCarAfterRide(
        @RequestPart("advertisementId") advertisementId: Long,
        @RequestPart("files") files: List<MultipartFile>
    ): ResponseEntity<*> {
        val result = rideService.checkupCar(
            advertisementId, files, AttachmentTypeEnum.TYPE_CAR_CHECK_PHOTO_AFTER
        )

        return ResponseEntity.ok(result)
    }

    @PostMapping("/sign/lessor")
    fun signActByLessor(@RequestPart("advertisementId") advertisementId: Long): ResponseEntity<*> {
        val result = rideService.signByLessor(advertisementId, Date())

        return ResponseEntity.ok(result)
    }

    @PostMapping("/sign/lessee")
    fun signActByLessee(@RequestPart("advertisementId") advertisementId: Long): ResponseEntity<*> {
        val result = rideService.signByLessee(advertisementId, Date())

        return ResponseEntity.ok(result)
    }

    @GetMapping("/get/all/user/{id}")
    fun getRidesByUserId(@PathVariable("id") userId: Long): ResponseEntity<List<Ride>> {
        val result = rideService.getRidesByUserId(userId)

        return ResponseEntity.ok(result)
    }

    @GetMapping("/get/all/advertisement/{id}")
    fun getRidesByAdvertisementId(@PathVariable("id") advertisementId: Long): ResponseEntity<List<Ride>> {
        val result = rideService.getRidesByAdvertisementId(advertisementId)

        return ResponseEntity.ok(result)
    }

    @GetMapping("/get/{id}")
    fun getRideDetails(@PathVariable("id") rideId: Long): ResponseEntity<Ride> {
        val result = rideService.getRideDetails(rideId)

        return ResponseEntity.ok(result)
    }

}