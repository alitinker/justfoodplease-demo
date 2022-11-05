package com.justfoodplease.app.justfoodplease.controllers

import com.justfoodplease.app.justfoodplease.auth.JwtResponse
import com.justfoodplease.app.justfoodplease.models.request.JfpUserRequestDto
import com.justfoodplease.app.justfoodplease.models.response.JfpUserResponseDto
import com.justfoodplease.app.justfoodplease.services.JfpUserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/auth")
class AuthController(
    private val jfpUserService: JfpUserService
) {

    @PostMapping("/signin")
    fun authenticateUser(@RequestBody jfpUser: JfpUserRequestDto): JwtResponse {
        return jfpUserService.signin(jfpUser.username, jfpUser.password)
    }

    @PostMapping("/signup")
    fun signup(@RequestBody jfpUser: JfpUserRequestDto): JfpUserResponseDto {
        return jfpUserService.signup(jfpUser)
    }
}