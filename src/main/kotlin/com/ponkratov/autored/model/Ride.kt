package com.ponkratov.autored.model;

import com.fasterxml.jackson.annotation.JsonIgnore
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "ride", schema = "autored")
class Ride(
    @Id @Column(name = "id") var id: Long = 0,
    @Basic @Column(name = "advertisement_id") var advertisementId: Long = 0,
    @Basic @Column(name = "lessor_id") var lessorId: Long = 0,
    @Basic @Column(name = "date_start") var dateStart: Date = Date(0),
    @Basic @Column(name = "date_end") var dateEnd: Date = Date(0),
    @Basic @Column(name = "date_signed_lessor") var dateSignedLessor: Date = Date(0),
    @Basic @Column(name = "date_signed_lessee") var dateSignedLessee: Date = Date(0),
    @Basic @Column(name = "chat_link") var chatLink: String = "",
    @Basic @Column(name = "payment_link") var paymentLink: String = "",
    @Basic @Column(name = "total_cost") var totalCost: Double = 0.0,
    @OneToOne @JoinColumn(
        name = "id",
        referencedColumnName = "id",
        nullable = false, insertable = false, updatable = false
    ) var supertypeEntityById: SupertypeEntity? = null,
    @ManyToOne @JsonIgnore @JoinColumn(
        name = "advertisement_id",
        referencedColumnName = "id",
        nullable = false, insertable = false, updatable = false
    ) var advertisementByAdvertisementId: Advertisement? = null,
    @ManyToOne @JsonIgnore @JoinColumn(
        name = "lessor_id",
        referencedColumnName = "id",
        nullable = false, insertable = false, updatable = false
    ) var userByLessorId: User? = null
)