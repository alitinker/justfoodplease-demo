package com.justfoodplease.app.justfoodplease.models.response

data class TagDto(
    val id: Int,
    val category: String,
    val subcategories: List<String>,
    val dateModified: String,
)
