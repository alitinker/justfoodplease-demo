package com.justfoodplease.app.justfoodplease.models.response

data class RecipeDto(
    val id: Int,
    val title: String,
    val subtitle: String? = null,
    val description: String? = null,
    val ingredients: List<RecipeIngredientDto>,
    val instructions: String? = null,
    val author: AuthorDto,
    val minutes: Int? = null,
    val serving: String? = null,
    val dateModified: String,
)
