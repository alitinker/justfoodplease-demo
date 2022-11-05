package com.justfoodplease.app.justfoodplease.models.request

data class RecipeCreationDto(
    val title: String,
    val subtitle: String? = null,
    val description: String? = null,
    val ingredients: List<RecipeIngredientCreationDto>,
    val instructions: String? = null,
    val authorId: String,
    val minutes: String,
    val serving: String? = null
)
