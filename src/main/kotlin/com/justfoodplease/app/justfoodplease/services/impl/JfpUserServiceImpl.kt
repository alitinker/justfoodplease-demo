package com.justfoodplease.app.justfoodplease.services.impl

import com.justfoodplease.app.justfoodplease.auth.JwtProvider
import com.justfoodplease.app.justfoodplease.auth.JwtResponse
import com.justfoodplease.app.justfoodplease.entities.JfpRoleAuthority
import com.justfoodplease.app.justfoodplease.entities.JfpUser
import com.justfoodplease.app.justfoodplease.entities.JfpUserRole
import com.justfoodplease.app.justfoodplease.exception.JfpException
import com.justfoodplease.app.justfoodplease.models.request.JfpUserRequestDto
import com.justfoodplease.app.justfoodplease.models.response.JfpUserResponseDto
import com.justfoodplease.app.justfoodplease.repositories.JfpRoleRepository
import com.justfoodplease.app.justfoodplease.repositories.JfpUserRepository
import com.justfoodplease.app.justfoodplease.services.JfpUserService
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class JfpUserServiceImpl(
    private val jwtProvider: JwtProvider,
    private val authenticationManager: AuthenticationManager,
    private val jfpUserRepository: JfpUserRepository,
    private val jfpRoleRepository: JfpRoleRepository,
    private val passwordEncoder: PasswordEncoder
): JfpUserService {

    private val logger: Logger = LogManager.getLogger(JfpUserServiceImpl::class.java)

    override fun signin(username: String, password: String): JwtResponse {
        return try {
            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(username, password))
            // get user & roles
            val jfpUser = jfpUserRepository.findOneByJfpUsername(username) ?:
                throw JfpException("User not found.", HttpStatus.NOT_FOUND)

            val roles = jfpUser.roles.map { it.jfpRole.jfpRoleAuthority }

            // create user token
            jwtProvider.createToken(username, roles)

        } catch (e: AuthenticationException) {
            logger.error(e.message)
            throw JfpException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY)
        }
    }

    override fun signup(jfpUserRequest: JfpUserRequestDto): JfpUserResponseDto {
        return try {
            if (!jfpUserRepository.existsByJfpUsername(jfpUserRequest.username)) {
                // map new user to entity
                val newUser = mapJfpUserRequestEntity(jfpUserRequest)
                // save new user
                val savedUser = jfpUserRepository.save(newUser)
                // map user to dto
                mapJfpUserToDto(savedUser)
            } else {
                throw JfpException("Username is already in use.", HttpStatus.UNPROCESSABLE_ENTITY)
            }
        } catch (e: JfpException) {
            logger.error(e.message)
            throw JfpException(e.message, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    private fun mapJfpUserToDto(jfpUser: JfpUser): JfpUserResponseDto {
        return JfpUserResponseDto(
            id = jfpUser.id ?: throw JfpException("Could not map Id for User", HttpStatus.INTERNAL_SERVER_ERROR),
            username = jfpUser.jfpUsername,
            roles = jfpUser.roles.map{ it.jfpRole }
        )
    }

    private fun mapJfpUserRequestEntity(jfpUserRequestDto: JfpUserRequestDto): JfpUser {
        val newUser =  JfpUser(
            jfpUsername = jfpUserRequestDto.username,
            jfpPassword = passwordEncoder.encode(jfpUserRequestDto.password),
            roles = emptyList<JfpUserRole>()
        )

        val userRoles = jfpUserRequestDto.roles.map {
            mapJfpUserRoleEntity(it, newUser)
        }

        newUser.roles = userRoles
        return newUser
    }

    private fun mapJfpUserRoleEntity(role: String, user: JfpUser): JfpUserRole {
        val roleAuthority = JfpRoleAuthority.valueOf(role)

        val jfpRole = jfpRoleRepository.findOneByJfpRoleAuthority(roleAuthority) ?:
            throw JfpException("Invalid role", HttpStatus.NOT_FOUND)

        return JfpUserRole(
            jfpRole = jfpRole,
            jfpUser = user
        )
    }


}