package com.ponkratov.autored.repository

import com.ponkratov.autored.model.Advertisement
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

interface AdvertisementRepository : JpaRepository<Advertisement, Long>{

    @Modifying
    @Query("update Advertisement set verified = true where id = :id")
    fun verify(id: Long): Boolean

    fun getAdvertisementById(id: Long): Advertisement
}