package com.ponkratov.autored.repository


import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Files
import java.nio.file.Paths

@Service
class AttachmentRepositoryLocal {

    //@Value("${repository.attachment-repository-local.base-path}")
    private var basePath: String = "E:\\CourceWork\\Files"

    fun saveFile(file: MultipartFile, name: String) {
        val bytes = file.bytes
        val path = Paths.get("${basePath}\\${name}.${file.getExtension()}")
        Files.write(path, bytes)
    }

    fun getFile(filename: String): Resource {
        val filePath = Paths.get("${basePath}\\${filename}")
        val resource = UrlResource(filePath.toUri())
        if (resource.exists() || resource.isReadable) {
            return resource;
        } else {
            throw RuntimeException("Could not read the file!");
        }
    }
}

fun MultipartFile.getExtension(): String {
    return requireNotNull(this.originalFilename).split(".")[1];
}