package com.ponkratov.autored.dto.response

import com.ponkratov.autored.model.User

class UserResponse(
    val user: User,
    val profilePhoto: String,
    val passportPhoto: String,
    val driverLicensePhoto: String
)