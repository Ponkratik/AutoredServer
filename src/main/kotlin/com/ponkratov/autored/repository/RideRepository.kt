package com.ponkratov.autored.repository

import com.ponkratov.autored.model.Ride
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Repository
interface RideRepository : JpaRepository<Ride, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Ride set dateStart = :dateStart where id = :id")
    fun setDateStartById(id: Long, dateStart: Date): Int

    @Transactional
    @Modifying
    @Query("UPDATE Ride set dateEnd = :dateEnd where id = :id")
    fun setDateEndById(id: Long, dateEnd: Date): Int

    @Transactional
    @Modifying
    @Query("UPDATE Ride set dateSignedLessor = :dateSignedLessor where id = :id")
    fun setDateSignedLessorById(id: Long, dateSignedLessor: Date): Int

    @Transactional
    @Modifying
    @Query("UPDATE Ride set dateSignedLessee = :dateSignedLessee where id = :id")
    fun setDateSignedLesseeById(id: Long, dateSignedLessee: Date): Int

    @Transactional
    @Modifying
    @Query("UPDATE Ride set chatLink = :chatLink where id = :id")
    fun setChatLinkById(id: Long, chatLink: String): Int

    @Transactional
    @Modifying
    @Query("UPDATE Ride set chatLink = :paymentLink where id = :id")
    fun setPaymentLinkById(id: Long, paymentLink: String): Int

    @Transactional
    @Modifying
    @Query("UPDATE Ride set totalCost = :totalCost where id = :id")
    fun setTotalCostById(id: Long, totalCost: Double): Int

    fun getAllByLessorId(lessorId: Long): List<Ride>

    fun getAllByAdvertisementId(advertisementId: Long): List<Ride>

    fun getRideById(id: Long): Ride
}