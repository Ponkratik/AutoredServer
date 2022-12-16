package com.ponkratov.autored.controller

import com.ponkratov.autored.dto.response.MessageResponse
import com.ponkratov.autored.model.ReviewUser
import com.ponkratov.autored.repository.ReviewUserRepository
import com.ponkratov.autored.service.ReviewUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController
@RequestMapping("/api/review/user")
class ReviewUserController {

    @Autowired
    private var _reviewUserService: ReviewUserService? = null
    private val reviewUserService get() = requireNotNull(_reviewUserService)

    @PostMapping("/add")
    fun addReview(@RequestPart("review") reviewUser: ReviewUser): ResponseEntity<*> {
        val result = reviewUserService.addReview(reviewUser)

        return ResponseEntity.ok(MessageResponse(result))
    }

    @GetMapping("/get/all")
    fun getAllReviews(): ResponseEntity<List<ReviewUser>> {
        val result = reviewUserService.getAllReviews()

        return ResponseEntity.ok(result)
    }

    @GetMapping("/get/all/{id}")
    fun getAllReviewsByUser(@PathVariable("id") userId: Long): ResponseEntity<List<ReviewUser>> {
        val result = reviewUserService.getAllReviewsByUserId(userId)

        return ResponseEntity.ok(result)
    }

    @GetMapping("/get/user/{id}/avg")
    fun getAvgMarkOfUser(@PathVariable("id") userId: Long): ResponseEntity<*> {
        val result = reviewUserService.getAvgMarkOfUser(userId)

        return ResponseEntity.ok(result)
    }

    @GetMapping("/get/{id}")
    fun getReviewDetails(@PathVariable id: Long): ResponseEntity<*> {
        val result = reviewUserService.getReviewUserDetails(id)

        return ResponseEntity.ok(result)
    }

    @DeleteMapping("/delete/{id}")
    fun deleteReviewById(@PathVariable id: Long): ResponseEntity<*> {
        val result = reviewUserService.deleteReviewById(id)

        return ResponseEntity.ok(MessageResponse(result))
    }
}