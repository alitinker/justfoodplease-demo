package com.justfoodplease.app.justfoodplease.models.response

data class RecipesCollection(
    val collection: List<RecipeDto> = emptyList(),
    val count: Int = collection.count()
)
