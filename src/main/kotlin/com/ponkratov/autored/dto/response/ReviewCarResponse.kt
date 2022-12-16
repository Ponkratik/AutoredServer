package com.ponkratov.autored.dto.response

import com.ponkratov.autored.model.Car
import com.ponkratov.autored.model.User

data class ReviewCarResponse(
    val id: Long,
    val mark: Int,
    val comment: String,
    val userFrom: Long,
    val carTo: Long,
    val userFromByUserId: User,
    val carToByCarId: Car
)