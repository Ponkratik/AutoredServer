package com.ponkratov.autored.repository

import com.ponkratov.autored.model.CarFeatureList
import org.springframework.data.jpa.repository.JpaRepository

interface CarFeatureListRepository : JpaRepository<CarFeatureList, Long> {

    fun findCarFeatureListByCarId(carId: Long): CarFeatureList
}