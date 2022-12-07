package com.ponkratov.autored.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "review_user", schema = "autored")
class ReviewUser(
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Id @Column(name = "id") var id: Long = 0,
    @Basic @Column(name = "mark") var mark: Int = 0,
    @Basic @Column(name = "comment") var comment: String? = null,
    @Basic @Column(name = "user_from") var userFrom: Long = 0,
    @Basic @Column(name = "user_to") var userTo: Long = 0,
    @ManyToOne @JoinColumn(
        name = "user_from", referencedColumnName = "id", nullable = false, insertable = false, updatable = false
    ) var userByUserFrom: User? = null,
    @ManyToOne @JoinColumn(
        name = "user_to", referencedColumnName = "id", nullable = false, insertable = false, updatable = false
    ) var userByUserTo: User? = null
)