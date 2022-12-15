package com.ponkratov.autored.service

import com.ponkratov.autored.model.AttachmentTypeEnum
import com.ponkratov.autored.model.SupportRequest
import com.ponkratov.autored.repository.SupportRequestRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class SupportRequestService {

    @Autowired
    private var _supportRequestRepository: SupportRequestRepository? = null
    private val supportRequestRepository get() = requireNotNull(_supportRequestRepository)

    @Autowired
    private var _supertypeEntityService: SupertypeEntityService? = null
    private val supertypeEntityService get() = requireNotNull(_supertypeEntityService)

    @Autowired
    private var _attachmentService: AttachmentService? = null
    private val attachmentService get() = requireNotNull(_attachmentService)

    fun createRequest(supportRequest: SupportRequest, files: List<MultipartFile>?): String {
        val generatedId = supertypeEntityService.getId()
        supportRequest.id = generatedId
        val requestResult = supportRequestRepository.save(supportRequest)

        files?.forEach {
            attachmentService.uploadFile(it, requestResult.id, AttachmentTypeEnum.TYPE_SUPPORT_REQUEST)
        }

        return "Request was created successully"
    }

    fun getAllRequests(): List<SupportRequest> {
        return supportRequestRepository.findAll()
    }

    fun getAllByUserId(userId: Long): List<SupportRequest> {
        return supportRequestRepository.findAllByUserId(userId)
    }

    fun getRequestDetails(requestId: Long): SupportRequest {
        return supportRequestRepository.findSupportRequestById(requestId)
    }
}