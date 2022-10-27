package com.ponkratov.autored.dto.request

import java.util.*

class RegisterRequest(
    var fio: String = "",
    var email: String = "",
    var rawPassword: String = "",
    var phone: String = "",
    var birthdate: Date = Date(),
    var passportNumber: String = "",
    var driverLicenseNumber: String = ""
    //сюда фотку паспорта и прав еще
)