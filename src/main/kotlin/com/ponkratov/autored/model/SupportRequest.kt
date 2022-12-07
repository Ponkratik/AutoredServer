package com.ponkratov.autored.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "support_request", schema = "autored")
class SupportRequest(
    @Id @Column(name = "id") var id: Long = 0,
    @Basic @Column(name = "user_id") var userId: Long = 0,
    @Basic @Column(name = "message") var message: String? = null,
    @OneToOne @JoinColumn(
        name = "id",
        referencedColumnName = "id",
        nullable = false, insertable = false, updatable = false
    ) var supertypeEntityById: SupertypeEntity? = null,
    @ManyToOne @JoinColumn(
        name = "user_id",
        referencedColumnName = "id",
        nullable = false, insertable = false, updatable = false
    ) var userByUserId: User? = null
)
