package com.justfoodplease.app.justfoodplease.auth

import com.justfoodplease.app.justfoodplease.exception.JfpException
import com.justfoodplease.app.justfoodplease.repositories.JfpUserRepository
import org.springframework.http.HttpStatus
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class JfpUserDetailsService(private val jfpUserRepository: JfpUserRepository) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val jfpUser = jfpUserRepository.findOneByJfpUsername(username) ?:
            throw JfpException("User not found.", HttpStatus.NOT_FOUND)

        return JfpUserDetails(jfpUser)
    }
}