package com.ponkratov.autored.service

import com.ponkratov.autored.model.ReviewUser
import com.ponkratov.autored.repository.ReviewUserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ReviewUserService {

    @Autowired
    private var _reviewUserRepository: ReviewUserRepository? = null
    private val reviewUserRepository get() = requireNotNull(_reviewUserRepository)

    fun addReview(reviewUser: ReviewUser): String {
        reviewUserRepository.save(reviewUser)

        return "Review added successfully"
    }

    fun getAllReviews(): List<ReviewUser> {
        return reviewUserRepository.findAll()
    }

    fun getAllReviewsByUserId(UserId: Long): List<ReviewUser> {
        return reviewUserRepository.getReviewsUserById(UserId)
    }

    fun getAvgMarkOfUser(UserId: Long): Double {
        return reviewUserRepository.getAvgMarkOfUser(UserId)
    }

    fun deleteReviewById(id: Long): String {
        val result = reviewUserRepository.deleteById(id)

        return "Review deleted successfully"
    }

    fun getReviewUserDetails(id: Long): ReviewUser {
        return reviewUserRepository.getReviewUserById(id)
    }
}