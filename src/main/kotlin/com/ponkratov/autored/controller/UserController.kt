package com.ponkratov.autored.controller

import com.ponkratov.autored.dto.request.LoginRequest
import com.ponkratov.autored.dto.request.RegisterRequest
import com.ponkratov.autored.dto.response.JwtResponse
import com.ponkratov.autored.dto.mapper.toUser
import com.ponkratov.autored.security.jwt.JwtUtils
import com.ponkratov.autored.security.service.UserDetailsImpl
import com.ponkratov.autored.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.util.stream.Collectors

@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
class UserController {

    @Autowired
    private var userService: UserService? = null

    @Autowired
    var authenticationManager: AuthenticationManager? = null

    @Autowired
    var jwtUtils: JwtUtils? = null

    //@PostMapping("/register")
    @PostMapping("/register", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun registerUser(
        @RequestPart("registerRequest") @Validated registerRequest: RegisterRequest,
        @RequestPart("passportPhoto") passportPhoto: MultipartFile,
        @RequestPart("driverLicensePhoto") driverLicensePhoto: MultipartFile
    ): ResponseEntity<*> {
        val result = requireNotNull(userService).registerUser(registerRequest.toUser(), passportPhoto, driverLicensePhoto)

        return ResponseEntity.ok(result)
    }

    @PostMapping("/login")
    fun login(@RequestBody @Validated loginRequest: LoginRequest): ResponseEntity<*> {
        val authentication = requireNotNull(authenticationManager).authenticate(
            UsernamePasswordAuthenticationToken(loginRequest.email, loginRequest.password)
        )
        SecurityContextHolder.getContext().authentication = authentication
        val jwt = requireNotNull(jwtUtils).generateJwtToken(authentication)
        val userDetails = authentication.principal as UserDetailsImpl
        val roles = userDetails.authorities
            .stream()
            .map { item: GrantedAuthority -> item.authority }
            .collect(Collectors.toList())

        return ResponseEntity.ok().body(
            JwtResponse(
                jwt,
                userDetails.id,
                userDetails.fio,
                userDetails.email,
                userDetails.phone,
                roles
            )
        )
    }
}