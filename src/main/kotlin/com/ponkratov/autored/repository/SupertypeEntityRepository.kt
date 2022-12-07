package com.ponkratov.autored.repository

import com.ponkratov.autored.model.SupertypeEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SupertypeEntityRepository : JpaRepository<SupertypeEntity, Long> {
}