package com.ponkratov.autored.model

import com.fasterxml.jackson.annotation.JsonIgnore
import java.util.Date
import javax.persistence.*

@Entity
@Table(name = "car", schema = "autored")
class Car(
    @Id @Column(name = "id") var id: Long = 0,
    @Basic @Column(name = "vin") var vin: String = "",
    @Basic @Column(name = "license_plate") var licensePlate: String = "",
    @Basic @Column(name = "make") var make: String = "",
    @Basic @Column(name = "model") var model: String = "",
    @Basic @Column(name = "manufactured_year") var manufacturedYear: Date = Date(),
    @Basic @Column(name = "transmission_type") var transmissionType: String = "",
    @Basic @Column(name = "fuel_type") var fuelType: String = "",
    @Basic @Column(name = "doors") var doors: Int = 0,
    @Basic @Column(name = "seats") var seats: Int = 0,
    @Basic @Column(name = "car_type") var carType: String = "",
    @Basic @Column(name = "color") var color: String = "",
    @OneToMany(mappedBy = "carByCarId") var advertisementsById: MutableList<Advertisement>? = null,
    @OneToOne @JsonIgnore @JoinColumn(
        name = "id",
        referencedColumnName = "id",
        nullable = false, insertable = false, updatable = false
    ) var supertypeEntityById: SupertypeEntity? = null,
    @OneToOne(mappedBy = "carByCarId") @JsonIgnore var carFeatureListById: CarFeatureList? = null,
    @OneToMany(mappedBy = "carByCarTo") @JsonIgnore var reviewCarsById: MutableList<ReviewCar>? = null
)
