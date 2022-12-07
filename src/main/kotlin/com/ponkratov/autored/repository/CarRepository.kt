package com.ponkratov.autored.repository

import com.ponkratov.autored.model.Car
import org.springframework.data.jpa.repository.JpaRepository

interface CarRepository: JpaRepository<Car, Long> {

    fun existsCarByVin(vin: String): Boolean

    fun existsCarByLicensePlate(licensePlate: String): Boolean
}