package com.ponkratov.autored.repository

import com.ponkratov.autored.dto.MapObjectStatistic
import com.ponkratov.autored.model.Advertisement
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.transaction.annotation.Transactional

interface AdvertisementRepository : JpaRepository<Advertisement, Long>{

    @Modifying
    @Query("update Advertisement set verified = true where id = :id")
    @Transactional
    fun verify(id: Long): Int

    fun getAdvertisementById(id: Long): Advertisement

    fun getAdvertisementsByUserId(userId: Long): List<Advertisement>

    @Query("select location from Advertisement group by location")
    fun getLocationsStatisticLocations(): List<String>

    @Query("select count(id) from Advertisement group by location")
    fun getLocationsStatisticCount(): List<Long>
}