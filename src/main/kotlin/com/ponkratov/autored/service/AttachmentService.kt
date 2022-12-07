package com.ponkratov.autored.service

import com.ponkratov.autored.model.Attachment
import com.ponkratov.autored.model.AttachmentTypeEnum
import com.ponkratov.autored.model.SupertypeEntity
import com.ponkratov.autored.repository.AttachmentRepository
import com.ponkratov.autored.repository.AttachmentRepositoryLocal
import com.ponkratov.autored.repository.getExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.Resource
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.UUID

@Service
class AttachmentService {

    @Autowired
    private var attachmentRepository: AttachmentRepository? = null

    @Autowired
    private var attachmentRepositoryLocal: AttachmentRepositoryLocal? = null

    fun uploadFile(file: MultipartFile, supertypeEntityId: Long, type: AttachmentTypeEnum): String {
        val dbResult = attachmentRepository?.save(
            Attachment(
                fileName = "${UUID.randomUUID()}.${file.getExtension()}",
                fileSize = file.size.toInt(),
                supertypeEntityId = supertypeEntityId,
                attachmentTypeId = type.id
            )
        )

        return if (dbResult != null) {
            val localResult = attachmentRepositoryLocal?.saveFile(file, dbResult.fileName)

            "File uploaded successfully"
        } else {
            "Error while uploading file"
        }
    }

    fun getAttachmentAsResource(filename: String): Resource {
        return requireNotNull(attachmentRepositoryLocal).getFile(filename)
    }

    fun getAttachmentInfo(id: Long): Attachment {
        return requireNotNull(attachmentRepository).getAttachmentById(id)
    }

    fun getAttachmentsBySupertype(supertypeId: Long): List<Attachment> {
        return requireNotNull(attachmentRepository).getAttachmentsBySupertypeEntityBySupertypeEntityId_Id(supertypeId)
    }
}