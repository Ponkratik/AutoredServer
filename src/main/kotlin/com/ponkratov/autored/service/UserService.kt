package com.ponkratov.autored.service

import com.ponkratov.autored.model.User
import com.ponkratov.autored.repository.RoleRepository
import com.ponkratov.autored.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService {

    @Autowired
    private val passwordEncoder: PasswordEncoder? = null

    @Autowired
    private val userRepository: UserRepository? = null

    @Autowired
    private val roleRepository: RoleRepository? = null

    fun registerUser(user: User): String {

        if (requireNotNull(userRepository).existsByEmail(user.email) || userRepository.existsByPhone(user.phone)) {
            return "User with email ${user.email} exists"
        }

        if (userRepository.existsByPhone(user.phone)) {
            return "User with phone ${user.phone} exists"
        }

        user.password = requireNotNull(passwordEncoder).encode(user.password)
        user.roles = requireNotNull(requireNotNull(roleRepository).findAllByName(ROLE_USER).toMutableSet())

        val result = userRepository.save(user)
        //вызвать сохранение фотографий
        return "User was registered successfully"
    }

    companion object {
        private const val ROLE_USER = "ROLE_USER"
    }
}