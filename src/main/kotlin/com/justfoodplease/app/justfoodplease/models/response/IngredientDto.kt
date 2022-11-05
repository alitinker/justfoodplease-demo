package com.justfoodplease.app.justfoodplease.models.response

data class IngredientDto(
        val id: Int,
        val name: String,
        val description: String,
        val url: String?,
        val dateModified: String
)
