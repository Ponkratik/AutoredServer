package com.ponkratov.autored.model;

import java.sql.Date
import javax.persistence.*

@Entity
@Table(name = "car", schema = "autored")
class Car(
    @Id @Column(name = "id") var id: Long = 0,
    @Basic @Column(name = "vin") var vin: String? = null,
    @Basic @Column(name = "license_plate") var licensePlate: String? = null,
    @Basic @Column(name = "make") var make: String? = null,
    @Basic @Column(name = "model") var model: String? = null,
    @Basic @Column(name = "manufactured_year") private var manufacturedYear: Date? = null,
    @Basic @Column(name = "transmission_type") var transmissionType: String? = null,
    @Basic @Column(name = "fuel_type") var fuelType: String? = null,
    @Basic @Column(name = "doors") var doors: Int = 0,
    @Basic @Column(name = "seats") var seats: Int = 0,
    @Basic @Column(name = "car_type") var carType: String? = null,
    @Basic @Column(name = "color") var color: String? = null,
    @OneToMany(mappedBy = "carByCarId") var advertisementsById: MutableList<Advertisement>? = null,
    @OneToOne @JoinColumn(
        name = "id",
        referencedColumnName = "id",
        nullable = false, insertable = false, updatable = false
    ) var supertypeEntityById: SupertypeEntity? = null,
    @OneToOne(mappedBy = "carByCarId") var carFeatureListById: CarFeatureList? = null,
    @OneToMany(mappedBy = "carByCarTo") var reviewCarsById: MutableList<ReviewCar>? = null
)
