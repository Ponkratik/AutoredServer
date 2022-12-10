package com.ponkratov.autored.repository

import com.ponkratov.autored.model.ReviewCar
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ReviewCarRepository : JpaRepository<ReviewCar, Long> {

    fun getReviewsCarById(id: Long): List<ReviewCar>

    fun getReviewCarById(id: Long): ReviewCar

    @Query("select avg(mark) from ReviewCar group by carTo having carTo = :carId")
    fun getAvgMarkOfCar(carId: Long): Double
}