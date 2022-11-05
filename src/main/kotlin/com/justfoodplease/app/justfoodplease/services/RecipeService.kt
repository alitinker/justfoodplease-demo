package com.justfoodplease.app.justfoodplease.services

import com.justfoodplease.app.justfoodplease.models.request.RecipeCreationDto
import com.justfoodplease.app.justfoodplease.models.response.RecipeDto
import com.justfoodplease.app.justfoodplease.models.response.RecipesCollection

interface RecipeService {

    fun getAllRecipes(): RecipesCollection

    fun createRecipe(recipe: RecipeCreationDto): RecipeDto

    fun updateRecipe(recipe: RecipeDto): RecipeDto
}