package com.ponkratov.autored.controller

import com.ponkratov.autored.service.AttachmentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.util.MimeTypeUtils
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Files
import java.nio.file.Paths
import javax.activation.MimeType
import javax.activation.MimetypesFileTypeMap

@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController
@RequestMapping("/api/attachment")
class AttachmentController {

    @Autowired
    private var attachmentService: AttachmentService? = null

    @GetMapping("/get/file/{filename:.+}")
    fun getAttachment(@PathVariable filename: String): ResponseEntity<Resource> {
        val file = attachmentService?.getAttachmentAsResource(filename)
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename\"${file?.filename}\"")
            .header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(Paths.get(file?.filename ?: "image/png")))
            .body(file)
    }

    @GetMapping("/get/id/{id}")
    fun getAttachmentInfo(@PathVariable id: Long): ResponseEntity<*> {
        val attachment = attachmentService?.getAttachmentInfo(id)
        return ResponseEntity.ok(attachment)
    }

    @GetMapping("/get/id/supertype/{supertypeId}")
    fun getAttachmentsBySupertype(@PathVariable supertypeId: Long): ResponseEntity<*> {
        val attachments = attachmentService?.getAttachmentsBySupertype(supertypeId)
        return ResponseEntity.ok(attachments)
    }
}