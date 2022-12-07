package com.ponkratov.autored.model.mapper

import com.ponkratov.autored.model.User
import com.ponkratov.autored.security.service.UserDetailsImpl
import org.springframework.security.core.authority.SimpleGrantedAuthority

fun User.toDetails(): UserDetailsImpl = UserDetailsImpl(
    id = id,
    fio = fio,
    email = email,
    password = password,
    blocked = blocked,
    verified = verified,
    roles = roles.map {
        SimpleGrantedAuthority(it.name)
    },
    phone = phone,
    birthdate = birthdate,
    profileDescription = profileDescription,
    passportNumber = passportNumber,
    driverLicenseNumber = driverLicenseNumber
)