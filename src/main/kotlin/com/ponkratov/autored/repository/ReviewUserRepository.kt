package com.ponkratov.autored.repository

import com.ponkratov.autored.model.ReviewUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ReviewUserRepository : JpaRepository<ReviewUser, Long> {

    fun getReviewsUserById(id: Long): List<ReviewUser>

    fun getReviewUserById(id: Long): ReviewUser

    @Query("select avg(mark) from ReviewUser group by userTo having userTo = :userId")
    fun getAvgMarkOfUser(userId: Long): Double
}