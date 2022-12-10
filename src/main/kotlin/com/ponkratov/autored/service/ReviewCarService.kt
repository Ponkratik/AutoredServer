package com.ponkratov.autored.service

import com.ponkratov.autored.model.ReviewCar
import com.ponkratov.autored.repository.ReviewCarRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ReviewCarService {

    @Autowired
    private var _reviewCarRepository: ReviewCarRepository? = null
    private val reviewCarRepository get() = requireNotNull(_reviewCarRepository)

    fun addReview(reviewCar: ReviewCar): String {
        reviewCarRepository.save(reviewCar)

        return "Review added successfully"
    }

    fun getAllReviews(): List<ReviewCar> {
        return reviewCarRepository.findAll()
    }

    fun getAllReviewsByCarId(carId: Long): List<ReviewCar> {
        return reviewCarRepository.getReviewsCarById(carId)
    }

    fun getAvgMarkOfCar(carId: Long): Double {
        return reviewCarRepository.getAvgMarkOfCar(carId)
    }

    fun deleteReviewById(id: Long): String {
        val result = reviewCarRepository.deleteById(id)

        return "Review deleted successfully"
    }

    fun getReviewCarDetails(id: Long): ReviewCar {
        return reviewCarRepository.getReviewCarById(id)
    }
}