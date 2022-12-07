package com.ponkratov.autored.model

import com.fasterxml.jackson.annotation.JsonIgnore
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "advertisement", schema = "autored")
class Advertisement(
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Id @Column(name = "id") var id: Long = 0,
    @Basic @Column(name = "user_id") var userId: Long = 0,
    @Basic @Column(name = "location") var location: String = "",
    @Basic @Column(name = "latitude") var latitude: Double = 0.0,
    @Basic @Column(name = "longitude") var longitude: Double = 0.0,
    @Basic @Column(name = "publication_date") var publicationDate: Date = Date(),
    @Basic @Column(name = "verified") var verified: Boolean = false,
    @Basic @Column(name = "car_id") var carId: Long = 0,
    @Basic @Column(name = "price_per_day") var pricePerDay: Double = 0.0,
    @Basic @Column(name = "price_per_week") var pricePerWeek: Double = 0.0,
    @Basic @Column(name = "price_per_month") var pricePerMonth: Double = 0.0,
    @ManyToOne @JsonIgnore @JoinColumn(
        name = "user_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false
    ) var userByUserId: User? = null,
    @ManyToOne @JsonIgnore @JoinColumn(
        name = "car_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false
    ) var carByCarId: Car? = null,
    @OneToMany(mappedBy = "advertisementByAdvertisementId") var ridesById: MutableList<Ride>? = null
)
