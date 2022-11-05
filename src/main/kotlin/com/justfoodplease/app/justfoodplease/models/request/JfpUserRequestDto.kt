package com.justfoodplease.app.justfoodplease.models.request

data class JfpUserRequestDto(
    val username: String,
    val password: String,
    val roles: List<String>
)
