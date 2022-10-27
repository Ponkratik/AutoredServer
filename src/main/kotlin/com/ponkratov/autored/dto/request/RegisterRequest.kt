package com.ponkratov.autored.model.request

import java.util.*

class RegisterRequest(
    private var email: String = "",
    private var password: String = "",
    private var phone: String = "",
    private var birthdate: Date = Date(),
    private var passportNumber: String = "",
    private var driverLicenseNumber: String = ""
    //сюда фотку паспорта и прав еще
)