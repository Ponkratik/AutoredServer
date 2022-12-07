package com.ponkratov.autored.model;

import java.sql.Timestamp
import javax.persistence.*

@Entity
@Table(name = "advertisement", schema = "autored")
class Advertisement(
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Id @Column(name = "id") private val id: Long = 0,
    @Basic @Column(name = "user_id") private val userId: Long = 0,
    @Basic @Column(name = "location") private val location: String? = null,
    @Basic @Column(name = "latitude") private val latitude: Double = 0.0,
    @Basic @Column(name = "longitude") private val longitude: Double = 0.0,
    @Basic @Column(name = "publication_date") private val publicationDate: Timestamp? = null,
    @Basic @Column(name = "verified") private val verified: Boolean = false,
    @Basic @Column(name = "car_id") private val carId: Long = 0,
    @Basic @Column(name = "price_per_day") private val pricePerDay: Double = 0.0,
    @Basic @Column(name = "price_per_week") private val pricePerWeek: Double = 0.0,
    @Basic @Column(name = "price_per_month") private val pricePerMonth: Double = 0.0,
    @ManyToOne @JoinColumn(
        name = "user_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false
    ) private val userByUserId: User? = null,
    @ManyToOne @JoinColumn(
        name = "car_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false
    ) private val carByCarId: Car? = null,
    @OneToMany(mappedBy = "advertisementByAdvertisementId") private val ridesById: Collection<Ride>? = null
)
