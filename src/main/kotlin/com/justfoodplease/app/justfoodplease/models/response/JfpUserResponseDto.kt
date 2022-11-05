package com.justfoodplease.app.justfoodplease.models.response

import com.justfoodplease.app.justfoodplease.entities.JfpRole

data class JfpUserResponseDto(
    val id: Int,
    val username: String,
    val roles: List<JfpRole>
)
