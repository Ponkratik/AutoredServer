package com.ponkratov.autored.model;

import javax.persistence.*;

@Entity
@Table(name = "role", schema = "autored")
class Role(
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Id @Column(name = "id") var id: Long = 0,
    @Basic @Column(name = "name") var name: String? = null,
    @OneToMany(mappedBy = "roleByRoleId") var userRolesById: MutableList<UserRole>? = null
)
