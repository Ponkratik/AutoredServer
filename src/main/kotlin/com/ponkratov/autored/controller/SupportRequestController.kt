package com.ponkratov.autored.controller

import com.ponkratov.autored.dto.response.MessageResponse
import com.ponkratov.autored.model.SupportRequest
import com.ponkratov.autored.service.SupportRequestService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController
@RequestMapping("/api/supportrequest")
class SupportRequestController {

    @Autowired
    private var _supportRequestService: SupportRequestService? = null
    private val supportRequestService get() = requireNotNull(_supportRequestService)

    @PostMapping("/add")
    fun addAdvertisement(
        @RequestPart("supportrequest") supportRequest: SupportRequest,
        @RequestPart("files") files: List<MultipartFile>?
    ): ResponseEntity<*> {
        val result = supportRequestService.createRequest(supportRequest, files)

        return ResponseEntity.ok(MessageResponse(result))
    }

    @GetMapping("/get/all")
    fun getAllRequests(): ResponseEntity<List<SupportRequest>> {
        val result = supportRequestService.getAllRequests()

        return ResponseEntity.ok(result)
    }

    @GetMapping("/get/all/{id}")
    fun getAllRequestsByUserId(@PathVariable("id") userId: Long): ResponseEntity<List<SupportRequest>> {
        val result = supportRequestService.getAllByUserId(userId)

        return ResponseEntity.ok(result)
    }

    @GetMapping("/get/{id}")
    fun getAllRequestId(@PathVariable("id") id: Long): ResponseEntity<SupportRequest> {
        val result = supportRequestService.getRequestDetails(id)

        return ResponseEntity.ok(result)
    }
}