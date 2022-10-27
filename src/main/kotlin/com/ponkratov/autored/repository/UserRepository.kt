package com.ponkratov.autored.repository

import com.ponkratov.autored.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {

    fun findByEmail(email: String): User?
    fun existsByEmail(email: String): Boolean
    fun existsByPhone(phone: String): Boolean
}