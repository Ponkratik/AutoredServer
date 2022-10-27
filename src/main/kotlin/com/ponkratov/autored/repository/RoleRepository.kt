package com.ponkratov.autored.repository

import com.ponkratov.autored.model.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RoleRepository : JpaRepository<Role, Long> {

    fun findAllByName(name: String): Set<Role>
}