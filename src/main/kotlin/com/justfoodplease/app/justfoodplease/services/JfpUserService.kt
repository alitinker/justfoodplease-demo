package com.justfoodplease.app.justfoodplease.services

import com.justfoodplease.app.justfoodplease.auth.JwtResponse
import com.justfoodplease.app.justfoodplease.models.request.JfpUserRequestDto
import com.justfoodplease.app.justfoodplease.models.response.JfpUserResponseDto

interface JfpUserService {

    fun signin(username: String, password: String): JwtResponse

    fun signup(jfpUserRequest: JfpUserRequestDto): JfpUserResponseDto
}