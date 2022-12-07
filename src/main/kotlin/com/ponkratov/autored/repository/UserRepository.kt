package com.ponkratov.autored.repository

import com.ponkratov.autored.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface UserRepository : JpaRepository<User, Long> {

    fun findByEmail(email: String): User?
    fun existsByEmail(email: String): Boolean
    fun existsByPhone(phone: String): Boolean

    @Modifying
    @Query("update User set verified = true where id = :id")
    @Transactional
    fun verify(id: Long): Int
}