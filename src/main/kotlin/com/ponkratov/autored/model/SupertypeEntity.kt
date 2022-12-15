package com.ponkratov.autored.model;

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "supertype_entity", schema = "autored")
class SupertypeEntity(
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Id @Column(name = "id") var id: Long = 0,
    @OneToMany(mappedBy = "supertypeEntityBySupertypeEntityId") @JsonIgnore var attachmentsById: MutableList<Attachment>? = null,
    @OneToOne(mappedBy = "supertypeEntityById") @JsonIgnore var carById: Car? = null,
    @OneToOne(mappedBy = "supertypeEntityById") @JsonIgnore var rideById: Ride? = null,
    @OneToOne(mappedBy = "supertypeEntityById") @JsonIgnore var supportRequestById: SupportRequest? = null,
    @OneToOne(mappedBy = "supertypeEntityById") @JsonIgnore var userById: User? = null
)