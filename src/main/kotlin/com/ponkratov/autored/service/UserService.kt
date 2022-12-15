package com.ponkratov.autored.service

import com.ponkratov.autored.model.AttachmentTypeEnum
import com.ponkratov.autored.model.RoleEnum
import com.ponkratov.autored.model.SupertypeEntity
import com.ponkratov.autored.model.User
import com.ponkratov.autored.repository.RoleRepository
import com.ponkratov.autored.repository.SupertypeEntityRepository
import com.ponkratov.autored.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class UserService {

    @Autowired
    private val passwordEncoder: PasswordEncoder? = null

    @Autowired
    private var _userRepository: UserRepository? = null
    private val userRepository get() = requireNotNull(_userRepository)

    @Autowired
    private val roleRepository: RoleRepository? = null

    @Autowired
    private val supertypeEntityRepository: SupertypeEntityRepository? = null

    @Autowired
    private val _attachmentService: AttachmentService? = null
    private val attachmentService get() = requireNotNull(_attachmentService)

    fun registerUser(user: User, avatarPhoto: MultipartFile, passportPhoto: MultipartFile, driverLicensePhoto: MultipartFile): String {

        if (userRepository.existsByEmail(user.email)) {
            return "User with email ${user.email} exists"
        }

        if (userRepository.existsByPhone(user.phone)) {
            return "User with phone ${user.phone} exists"
        }

        user.password = requireNotNull(passwordEncoder).encode(user.password)
        user.roles = requireNotNull(roleRepository).findAllByName(RoleEnum.ROLE_USER.name).toMutableSet()

        val idObject = supertypeEntityRepository?.save(SupertypeEntity())
        user.id = requireNotNull(idObject?.id)
        val registerResult = userRepository.save(user)

        val avatarUploadResult = attachmentService.uploadFile(avatarPhoto, user.id, AttachmentTypeEnum.TYPE_AVATAR)
        val passportUploadResult = attachmentService.uploadFile(passportPhoto, user.id, AttachmentTypeEnum.TYPE_PASSPORT)
        val driverLicenseUploadResult = attachmentService.uploadFile(driverLicensePhoto, user.id, AttachmentTypeEnum.TYPE_DRIVER_LICENSE)

        return "User was registered successfully"
    }

    fun verifyUser(id: Long): String {
        val result = userRepository.verify(id)
        return if (result > 0) {
            "User verified successfully"
        } else {
            "Wrong user ID(${id})"
        }
    }

    fun getUserById(id: Long): User {
        return userRepository.getUserById(id)
    }
}