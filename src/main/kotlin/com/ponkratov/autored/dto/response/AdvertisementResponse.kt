package com.ponkratov.autored.dto.response

import com.ponkratov.autored.model.Advertisement
import com.ponkratov.autored.model.Car
import com.ponkratov.autored.model.CarFeatureList

class AdvertisementResponse(
    val advertisement: Advertisement,
    val car: Car,
    val carFeatureList: CarFeatureList,
    val photoPaths: List<String>
)