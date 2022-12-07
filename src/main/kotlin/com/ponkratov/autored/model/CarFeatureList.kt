package com.ponkratov.autored.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "car_feature_list", schema = "autored")
class CarFeatureList(
    @Id @Column(name = "car_id") var carId: Long = 0,
    @Basic @Column(name = "conditioner") var isConditioner: Boolean = false,
    @Basic @Column(name = "all_wheel_drive") var isAllWheelDrive: Boolean = false,
    @Basic @Column(name = "leather_seats") var isLeatherSeats: Boolean = false,
    @Basic @Column(name = "child_seats") var isChildSeats: Boolean = false,
    @Basic @Column(name = "heated_seats") var isHeatedSeats: Boolean = false,
    @Basic @Column(name = "cooled_seats") var isCooledSeats: Boolean = false,
    @Basic @Column(name = "gps") var isGps: Boolean = false,
    @Basic @Column(name = "ski_rack") var isSkiRack: Boolean = false,
    @Basic @Column(name = "audio_input") var isAudioInput: Boolean = false,
    @Basic @Column(name = "usb_input") var isUsbInput: Boolean = false,
    @Basic @Column(name = "bluetooth") var isBluetooth: Boolean = false,
    @Basic @Column(name = "android_auto") var isAndroidAuto: Boolean = false,
    @Basic @Column(name = "apple_carplay") var isAppleCarplay: Boolean = false,
    @Basic @Column(name = "sun_roof") var isSunRoof: Boolean = false,
    @OneToOne @JoinColumn(
        name = "car_id",
        referencedColumnName = "id",
        nullable = false,
        insertable = false,
        updatable = false
    ) var carByCarId: Car? = null
)
