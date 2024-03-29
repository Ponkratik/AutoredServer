package com.ponkratov.autored.controller

import com.ponkratov.autored.dto.response.MessageResponse
import com.ponkratov.autored.dto.response.RideResponse
import com.ponkratov.autored.model.AttachmentTypeEnum
import com.ponkratov.autored.model.Ride
import com.ponkratov.autored.service.RideService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.util.*

@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController
@RequestMapping("/api/ride")
class RideController {

    @Autowired
    private var _rideService: RideService? = null
    private val rideService get() = requireNotNull(_rideService)

    @PostMapping("/book/book")
    fun bookRide(
        @RequestPart("advertisementId") advertisementId: Long,
        @RequestPart("lessorId") lessorId: Long
    ): ResponseEntity<*> {
        val result = rideService.startBooking(
            advertisementId,
            lessorId,
            Date(0)
        )
        return ResponseEntity.ok(MessageResponse(result))
    }

    @PostMapping("/book/start/{id}")
    fun startRide(
        @PathVariable("id") rideId: Long
    ): ResponseEntity<*> {
        val result = rideService.startRide(
            rideId,
            Date()
        )
        return ResponseEntity.ok(MessageResponse(result))
    }

    @PostMapping("/book/end/{id}")
    fun endRide(
        @PathVariable("id") rideId: Long
    ): ResponseEntity<*> {
        val result = rideService.endRide(
            rideId,
            Date()
        )
        return ResponseEntity.ok(MessageResponse(result))
    }

    @PostMapping("/checkup/before")
    fun checkupCarBeforeRide(
        @RequestPart("rideId") rideId: Long,
        @RequestPart("files") files: List<MultipartFile>
    ): ResponseEntity<*> {
        val result = rideService.checkupCar(
            rideId, files, AttachmentTypeEnum.TYPE_CAR_CHECK_PHOTO_BEFORE
        )

        return ResponseEntity.ok(MessageResponse(result))
    }

    @PostMapping("/checkup/after")
    fun checkupCarAfterRide(
        @RequestPart("ridetId") rideId: Long,
        @RequestPart("files") files: List<MultipartFile>
    ): ResponseEntity<*> {
        val result = rideService.checkupCar(
            rideId, files, AttachmentTypeEnum.TYPE_CAR_CHECK_PHOTO_AFTER
        )

        return ResponseEntity.ok(MessageResponse(result))
    }

    @PostMapping("/sign/lessor/{id}")
    fun signActByLessor(@PathVariable("id") rideId: Long): ResponseEntity<*> {
        val result = rideService.signByLessor(rideId, Date())

        return ResponseEntity.ok(MessageResponse(result))
    }

    @PostMapping("/sign/lessee/{id}")
    fun signActByLessee(@PathVariable("id") rideId: Long): ResponseEntity<*> {
        val result = rideService.signByLessee(rideId, Date())

        return ResponseEntity.ok(MessageResponse(result))
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

    @GetMapping("/get/all/full/advertisement/{id}")
    fun getRideResponsesByAdvertisementId(@PathVariable("id") advertisementId: Long): ResponseEntity<List<RideResponse>> {
        val result = rideService.getRideResponsesByAdvertisementId(advertisementId)

        return ResponseEntity.ok(result)
    }

    @GetMapping("/get/all/full/lessor/{id}")
    fun getRideResponsesByLessorId(@PathVariable("id") lessorId: Long): ResponseEntity<List<RideResponse>> {
        val result = rideService.getRideResponsesByLessorId(lessorId)

        return ResponseEntity.ok(result)
    }
}