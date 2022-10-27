package com.ponkratov.autored.dto

import com.ponkratov.autored.dto.request.RegisterRequest
import com.ponkratov.autored.model.User

fun RegisterRequest.toUser(): User {
    return User(
        fio = fio,
        email = email,
        password = rawPassword,
        phone = phone,
        birthdate = birthdate,
        passportNumber = passportNumber,
        driverLicenseNumber = driverLicenseNumber
    )
}