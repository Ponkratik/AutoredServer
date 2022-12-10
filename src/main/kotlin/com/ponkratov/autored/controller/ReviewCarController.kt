package com.ponkratov.autored.controller

import com.ponkratov.autored.model.ReviewCar
import com.ponkratov.autored.service.ReviewCarService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController
@RequestMapping("/api/review/car")
class ReviewCarController {

    @Autowired
    private var _reviewCarService: ReviewCarService? = null
    private val reviewCarService get() = requireNotNull(_reviewCarService)

    @PostMapping("/add")
    fun addReview(@RequestPart("review") reviewCar: ReviewCar): ResponseEntity<*> {
        val result = reviewCarService.addReview(reviewCar)

        return ResponseEntity.ok(result)
    }

    @GetMapping("/get/all")
    fun getAllReviews(): ResponseEntity<List<ReviewCar>> {
        val result = reviewCarService.getAllReviews()

        return ResponseEntity.ok(result)
    }

    @GetMapping("/get/all/{id}")
    fun getAllReviewsByCar(@PathVariable("id") carId: Long): ResponseEntity<List<ReviewCar>> {
        val result = reviewCarService.getAllReviewsByCarId(carId)

        return ResponseEntity.ok(result)
    }

    @GetMapping("/get/car/{id}/avg")
    fun getAvgMarkOfCar(@PathVariable("id") carId: Long): ResponseEntity<*> {
        val result = reviewCarService.getAvgMarkOfCar(carId)

        return ResponseEntity.ok(result)
    }

    @GetMapping("/get/{id}")
    fun getReviewDetails(@PathVariable id: Long): ResponseEntity<*> {
        val result = reviewCarService.getReviewCarDetails(id)

        return ResponseEntity.ok(result)
    }

    @DeleteMapping("/delete/{id}")
    fun deleteReviewById(@PathVariable id: Long): ResponseEntity<*> {
        val result = reviewCarService.deleteReviewById(id)

        return ResponseEntity.ok(result)
    }
}