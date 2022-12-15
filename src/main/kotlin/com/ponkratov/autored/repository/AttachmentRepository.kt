package com.ponkratov.autored.repository

import com.ponkratov.autored.model.Attachment
import com.ponkratov.autored.model.AttachmentType
import com.ponkratov.autored.model.SupertypeEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AttachmentRepository : JpaRepository<Attachment, Long> {

    fun getAttachmentsBySupertypeEntityBySupertypeEntityId_Id(supertypeEntityBySupertypeEntityId_id: Long): List<Attachment>

    fun getAttachmentsBySupertypeEntityBySupertypeEntityId_IdAndAttachmentTypeByAttachmentTypeId_Id(
        supertypeEntityBySupertypeEntityId_id: Long, attachmentTypeByAttachmentTypeId_id: Long
    ): List<Attachment>

    fun getAttachmentById(id: Long): Attachment
}