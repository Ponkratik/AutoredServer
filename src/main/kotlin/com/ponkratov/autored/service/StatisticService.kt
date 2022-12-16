package com.ponkratov.autored.service

import com.ponkratov.autored.dto.MapObjectStatistic
import com.ponkratov.autored.dto.response.StatisticResponse
import com.ponkratov.autored.repository.AdvertisementRepository
import com.ponkratov.autored.repository.CarRepository
import com.ponkratov.autored.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class StatisticService {

    @Autowired
    private var _userRepository: UserRepository? = null
    private val userRepository get() = requireNotNull(_userRepository)

    @Autowired
    private var _advertisementRepository: AdvertisementRepository? = null
    private val advertisementRepository get() = requireNotNull(_advertisementRepository)

    @Autowired
    private var _carRepository: CarRepository? = null
    private val carRepository get() = requireNotNull(_carRepository)

    fun getStatisticResponse(): StatisticResponse {
        val response1 = carRepository.getCarsStatisticMake()
        val response11 = carRepository.getCarsStatisticCount()
        val list1 = mutableListOf<MapObjectStatistic>()
        for (i in response1.indices) {
            list1.add(MapObjectStatistic(response1[i], response11[i]))
        }

        val response2 = advertisementRepository.getLocationsStatisticLocations()
        val response22 = advertisementRepository.getLocationsStatisticCount()
        val list2 = mutableListOf<MapObjectStatistic>()
        for (i in response2.indices) {
            list2.add(MapObjectStatistic(response2[i], response22[i]))
        }

        return StatisticResponse(
            userRepository.count(),
            advertisementRepository.count(),
            list1,
            list2
        )
    }
}