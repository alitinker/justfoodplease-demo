package com.justfoodplease.app.justfoodplease.models.response

data class IngredientsCollection(
    val collection: List<IngredientDto> = emptyList(),
    val count: Int = collection.count()
)
