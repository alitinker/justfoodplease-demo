package com.justfoodplease.app.justfoodplease.models.request

data class RecipeIngredientCreationDto (
    val ingredientId: Int,
    val measurementUnitId: Int,
    val quantity: String
    )