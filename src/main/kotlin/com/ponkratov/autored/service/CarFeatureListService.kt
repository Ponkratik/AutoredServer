package com.ponkratov.autored.service

import com.ponkratov.autored.model.CarFeatureList
import com.ponkratov.autored.repository.CarFeatureListRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CarFeatureListService {

    @Autowired
    private var carFeatureListRepository: CarFeatureListRepository? = null

    fun addCarFeatureList(carFeatureList: CarFeatureList): Boolean {
        val result = carFeatureListRepository?.save(carFeatureList)
        return result != null
    }
}