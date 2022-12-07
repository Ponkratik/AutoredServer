package com.ponkratov.autored.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_role", schema = "autored")
class UserRole(
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Id @Column(name = "id") var id: Long = 0,
    @Basic @Column(name = "role_id") var roleId: Long = 0,
    @Basic @Column(name = "user_id") var userId: Long = 0,
    @ManyToOne @JoinColumn(
        name = "role_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false
    ) var roleByRoleId: Role,
    @ManyToOne @JoinColumn(
        name = "user_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false
    ) var userByUserId: User
)
