package com.ponkratov.autored.security.service

import com.ponkratov.autored.model.User
import com.ponkratov.autored.model.mapper.toDetails
import com.ponkratov.autored.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserDetailsServiceImpl : UserDetailsService {

    @Autowired
    var userRepository: UserRepository? = null

    @Transactional
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(email: String): UserDetails {
        val user: User = userRepository?.findByEmail(email)
            ?: throw UsernameNotFoundException("User was not found with email: $email")
        return user.toDetails()
    }
}