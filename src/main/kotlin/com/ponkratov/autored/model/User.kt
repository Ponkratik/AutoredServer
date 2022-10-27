package com.ponkratov.autored.model

import java.util.*
import javax.persistence.*
import kotlin.collections.HashSet


@Entity
@Table(name = "user")
class User(
    @Id @GeneratedValue @Column(name = "id") var id: Long = 0,
    @Column(name = "fio") var fio: String = "",
    @Column(name = "email") var email: String = "",
    @Column(name = "password") var password: String = "",
    @Column(name = "blocked") var blocked: Boolean = false,
    @Column(name = "verified") var verified: Boolean = false,
    @ManyToMany(fetch = FetchType.EAGER) var roles: MutableSet<Role> = hashSetOf(),
    @Column(name = "phone") var phone: String = "",
    @Column(name = "birthdate") var birthdate: Date = Date(),
    @Column(name = "profile_description") var profileDescription: String = "",
    @Column(name = "passport_number") var passportNumber: String = "",
    @Column(name = "driver_license_number") var driverLicenseNumber: String = ""
)