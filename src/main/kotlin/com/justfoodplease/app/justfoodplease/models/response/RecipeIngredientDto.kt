package com.justfoodplease.app.justfoodplease.models.response

data class RecipeIngredientDto(
    val id: Int,
    val recipeId: Int,
    val ingredient: IngredientDto,
    val measurementUnit: MeasurementUnitDto?,
    val quantity: String,
    val dateModified: String?
)
