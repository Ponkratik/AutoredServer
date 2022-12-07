package com.ponkratov.autored.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "ride", schema = "autored")
class Ride(
    @Id @Column(name = "id") var id: Long = 0,
    @Basic @Column(name = "advertisement_id") var advertisementId: Long = 0,
    @Basic @Column(name = "lessor_id") var lessorId: Long = 0,
    @Basic @Column(name = "date_start") private var dateStart: Timestamp? = null,
    @Basic @Column(name = "date_end") private var dateEnd: Timestamp? = null,
    @Basic @Column(name = "date_signed_lessor") private var dateSignedLessor: Timestamp? = null,
    @Basic @Column(name = "date_signed_lessee") private var dateSignedLessee: Timestamp? = null,
    @Basic @Column(name = "chat_link") var chatLink: String? = null,
    @Basic @Column(name = "payment_link") var paymentLink: String? = null,
    @Basic @Column(name = "total_cost") var totalCost: String? = null,
    @OneToOne @JoinColumn(
        name = "id",
        referencedColumnName = "id",
        nullable = false, insertable = false, updatable = false
    ) var supertypeEntityById: SupertypeEntity? = null,
    @ManyToOne @JoinColumn(
        name = "advertisement_id",
        referencedColumnName = "id",
        nullable = false, insertable = false, updatable = false
    ) var advertisementByAdvertisementId: Advertisement? = null,
    @ManyToOne @JoinColumn(
        name = "lessor_id",
        referencedColumnName = "id",
        nullable = false, insertable = false, updatable = false
    ) var userByLessorId: User? = null
)