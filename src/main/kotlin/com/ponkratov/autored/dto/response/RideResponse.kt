package com.ponkratov.autored.dto.response

import com.ponkratov.autored.model.Ride
import com.ponkratov.autored.model.User

class RideResponse(
    val ride: Ride,
    val user: User,
    val advertisementResponse: AdvertisementResponse
)