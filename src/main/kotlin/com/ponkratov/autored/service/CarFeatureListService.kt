package com.ponkratov.autored.service

import com.ponkratov.autored.model.CarFeatureList
import com.ponkratov.autored.repository.CarFeatureListRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CarFeatureListService {

    @Autowired
    private var _carFeatureListRepository: CarFeatureListRepository? = null
    private val carFeatureListRepository get() = requireNotNull(_carFeatureListRepository)

    fun addCarFeatureList(carFeatureList: CarFeatureList): Boolean {
        val result = carFeatureListRepository.save(carFeatureList)
        return true
    }

    fun getCarFeatureList(id: Long): CarFeatureList {
        return carFeatureListRepository.findCarFeatureListByCarId(id)
    }
}