package com.ponkratov.autored.service

import com.ponkratov.autored.model.SupertypeEntity
import com.ponkratov.autored.repository.SupertypeEntityRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SupertypeEntityService {

    @Autowired
    private var _supertypeEntityRepository: SupertypeEntityRepository? = null
    private val supertypeEntityRepository get() = requireNotNull(_supertypeEntityRepository)

    fun getId(): Long {
        val result = supertypeEntityRepository.save(SupertypeEntity())
        return result.id
    }
}