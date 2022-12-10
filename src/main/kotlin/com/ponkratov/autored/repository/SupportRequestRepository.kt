package com.ponkratov.autored.repository

import com.ponkratov.autored.model.SupportRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SupportRequestRepository : JpaRepository<SupportRequest, Long> {

    fun findSupportRequestById(id: Long): SupportRequest

    fun findAllByUserId(userId: Long): List<SupportRequest>
}