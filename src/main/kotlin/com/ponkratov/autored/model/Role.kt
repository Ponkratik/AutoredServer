package com.ponkratov.autored.model

import javax.persistence.*

@Entity
@Table(name = "role")
class Role(
    @Id @GeneratedValue @Column(name = "id") var id: Long = 0,
    @Column(name = "name") var name: String = ""
)