package com.ponkratov.autored.security.service

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

class UserDetailsImpl(
    var id: Long = 0,
    var fio: String = "",
    var email: String = "",
    private var password: String = "",
    var blocked: Boolean = false,
    var verified: Boolean = false,
    val roles: Collection<GrantedAuthority>,
    var phone: String = "",
    var birthdate: Date = Date(),
    var profileDescription: String = "",
    var passportNumber: String = "",
    var driverLicenseNumber: String = ""
) : UserDetails {

    override fun getAuthorities(): Collection<GrantedAuthority> = roles

    //Java-conflict
    override fun getPassword(): String = password

    override fun getUsername(): String = email

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = !blocked

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = verified
}