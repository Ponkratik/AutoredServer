package com.ponkratov.autored.dto.response

class JwtResponse(
    var accessToken: String,
    var id: Long,
    var fio: String,
    var email: String,
    var phone: String,
    var roles: List<String>
) {
    private var tokenType = "Bearer"
}