package com.ponkratov.autored.service

import com.ponkratov.autored.dto.response.AdvertisementResponse
import com.ponkratov.autored.model.Advertisement
import com.ponkratov.autored.model.Car
import com.ponkratov.autored.model.CarFeatureList
import com.ponkratov.autored.repository.AdvertisementRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.*

@Service
class AdvertisementService {

    @Autowired
    private var _advertisementRepository: AdvertisementRepository? = null
    private val advertisementRepository get() = requireNotNull(_advertisementRepository)

    @Autowired
    private var _carService: CarService? = null
    private val carService get() = requireNotNull(_carService)

    fun addAdvertisement(
        advertisement: Advertisement,
        car: Car,
        carFeatures: CarFeatureList,
        carPhotos: List<MultipartFile>
    ): String {
        val carResult = carService.addCar(car, carFeatures, carPhotos)
        if (carResult == -1L) {
            return "Car with this VIN(${car.vin}) is exists"
        }

        if (carResult == -2L) {
            return "Car with this license plate(${car.licensePlate}) is exists"
        }

        advertisement.carId = carResult
        advertisement.publicationDate = Date()
        advertisementRepository.save(advertisement)
        return "Advertisement created successfully"
    }

    fun verifyAdvertisement(id: Long): String {
        val result = advertisementRepository.verify(id)
        return if (result > 0) {
            "Advertisement verified successfully"
        } else {
            "Wrong advertisement ID(${id})"
        }
    }

    fun getAdvertisement(id: Long): Advertisement {
        return advertisementRepository.getAdvertisementById(id)
    }

    fun getAdvertisements(): List<Advertisement> {
        return advertisementRepository.findAll()
    }

    fun getAdvertisementsByUser(userId: Long): List<Advertisement> {
        return advertisementRepository.getAdvertisementsByUserId(userId)
    }

    fun getAdvertisementResponse(id: Long): AdvertisementResponse {
        val adv = getAdvertisement(id)
        return AdvertisementResponse(
            adv,
            carService.getCar(adv.carId),
            carService.getCarFeaturesList(adv.carId),
            carService.getCarPhotosList(adv.carId)
        )
    }

    fun getAdvertisementsResponseByUser(userId: Long): List<AdvertisementResponse> {
        val result = getAdvertisementsByUser(userId)

        return result.map {
            AdvertisementResponse(
                it,
                carService.getCar(it.carId),
                carService.getCarFeaturesList(it.carId),
                carService.getCarPhotosList(it.carId)
            )
        }
    }

    fun getAdvertisementsResponse(): List<AdvertisementResponse> {
        val result = getAdvertisements()

        return result.map {
            AdvertisementResponse(
                it,
                carService.getCar(it.carId),
                carService.getCarFeaturesList(it.carId),
                carService.getCarPhotosList(it.carId)
            )
        }
    }
}