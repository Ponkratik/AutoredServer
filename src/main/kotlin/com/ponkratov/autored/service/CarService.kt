package com.ponkratov.autored.service

import com.ponkratov.autored.model.AttachmentTypeEnum
import com.ponkratov.autored.model.Car
import com.ponkratov.autored.model.CarFeatureList
import com.ponkratov.autored.repository.CarRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.Resource
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class CarService {

    @Autowired
    private var _carRepository: CarRepository? = null
    private val carRepository get() = requireNotNull(_carRepository)

    @Autowired
    private var _supertypeEntityService: SupertypeEntityService? = null
    private val supertypeEntityService get() = requireNotNull(_supertypeEntityService)

    @Autowired
    private var _carFeatureListService: CarFeatureListService? = null
    private val carFeatureListService get() = requireNotNull(_carFeatureListService)

    @Autowired
    private var _attachmentService: AttachmentService? = null
    private val attachmentService get() = requireNotNull(_attachmentService)

    fun addCar(car: Car, carFeatures: CarFeatureList, photos: List<MultipartFile>): Long {
        if (carRepository.existsCarByVin(car.vin)) {
            return -1
        }

        if (carRepository.existsCarByLicensePlate(car.licensePlate)) {
            return -2
        }

        val generatedId = supertypeEntityService.getId()
        car.id = generatedId
        val carResult = carRepository.save(car)

        carFeatures.carId = carResult.id
        val carFeatureListResult = carFeatureListService.addCarFeatureList(carFeatures)

        photos.forEach {
            attachmentService.uploadFile(it, carResult.id, AttachmentTypeEnum.TYPE_CAR_PHOTO)
        }

        return car.id
    }

    fun getCar(id: Long): Car {
        return carRepository.findCarById(id)
    }

    fun getCarFeaturesList(id: Long): CarFeatureList {
        return carFeatureListService.getCarFeatureList(id)
    }

    fun getCarPhotosList(id: Long): List<String> {
        val result = attachmentService.getAttachmentsBySupertypeAndType(id, AttachmentTypeEnum.TYPE_CAR_PHOTO)
        return result.map {
            it.fileName
        }
    }
}