package com.ponkratov.autored.repository

import com.ponkratov.autored.dto.MapObjectStatistic
import com.ponkratov.autored.model.Car
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface CarRepository: JpaRepository<Car, Long> {

    fun existsCarByVin(vin: String): Boolean

    fun existsCarByLicensePlate(licensePlate: String): Boolean

    fun findCarById(id: Long): Car

    @Query("select make from Car group by make")
    fun getCarsStatisticMake(): List<String>
    @Query("select count(id) from Car group by make")
    fun getCarsStatisticCount(): List<Long>
}