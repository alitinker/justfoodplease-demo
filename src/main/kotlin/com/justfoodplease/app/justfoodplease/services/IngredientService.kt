package com.justfoodplease.app.justfoodplease.services

import com.justfoodplease.app.justfoodplease.models.request.IngredientCreationDto
import com.justfoodplease.app.justfoodplease.models.response.IngredientDto
import com.justfoodplease.app.justfoodplease.models.response.IngredientsCollection

interface IngredientService {

    fun getAllIngredients(): IngredientsCollection

    fun createIngredient(ingredient: IngredientCreationDto): IngredientDto
}