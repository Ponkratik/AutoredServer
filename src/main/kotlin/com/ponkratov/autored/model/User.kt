package com.ponkratov.autored.model;

import java.util.*
import javax.persistence.*


@Entity
@Table(name = "user", schema = "autored")
class User(
    @Id @Column(name = "id") var id: Long = 0,
    @Basic @Column(name = "fio") var fio: String = "",
    @Basic @Column(name = "email") var email: String = "",
    @Basic @Column(name = "password") var password: String = "",
    @Basic @Column(name = "blocked") var isBlocked: Boolean = false,
    @Basic @Column(name = "verified") var isVerified: Boolean = false,
    @Basic @Column(name = "phone") var phone: String = "",
    @Basic @Column(name = "birthdate") var birthdate: Date = Date(),
    @Basic @Column(name = "profile_description") var profileDescription: String = "",
    @Basic @Column(name = "passport_number") var passportNumber: String = "",
    @Basic @Column(name = "driver_license_number") var driverLicenseNumber: String = "",
) {
    @OneToMany(mappedBy = "userByUserId")
    var advertisementsById: MutableList<Advertisement>? = null

    @OneToMany(mappedBy = "userByUserFrom")
    var reviewCarsById: MutableList<ReviewCar>? = null

    @OneToMany(mappedBy = "userByUserFrom")
    var reviewUsersByIdPosted: MutableList<ReviewUser>? = null

    @OneToMany(mappedBy = "userByUserTo")
    var reviewUsersByIdRecieved: MutableList<ReviewUser>? = null

    @OneToMany(mappedBy = "userByLessorId")
    var ridesById: MutableList<Ride>? = null

    @OneToMany(mappedBy = "userByUserId")
    var supportRequestsById: MutableList<SupportRequest>? = null

    @OneToOne
    @JoinColumn(
        name = "id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false
    )
    var supertypeEntityById: SupertypeEntity? = null

    @ManyToMany(fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @JoinTable(
        name = "user_role",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id")]
    )
    var roles: MutableSet<Role> = mutableSetOf()
}