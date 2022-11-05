package com.justfoodplease.app.justfoodplease.services.impl

import com.justfoodplease.app.justfoodplease.entities.Ingredient
import com.justfoodplease.app.justfoodplease.models.request.IngredientCreationDto
import com.justfoodplease.app.justfoodplease.models.response.IngredientDto
import com.justfoodplease.app.justfoodplease.models.response.IngredientsCollection
import com.justfoodplease.app.justfoodplease.repositories.IngredientRepository
import com.justfoodplease.app.justfoodplease.services.IngredientService
import org.springframework.stereotype.Service

@Service
class IngredientServiceImpl (
    private val ingredientRepository : IngredientRepository,
) : IngredientService {

    override fun getAllIngredients(): IngredientsCollection {
        // TODO: wrap in try catch
        val ingredients = ingredientRepository.findAll().map {
            it.toIngredientDto()
        }
        return IngredientsCollection(
            collection = ingredients
        )
    }

    override fun createIngredient(ingredient: IngredientCreationDto): IngredientDto {
        // TODO: wrap in try catch
        val newIngredient = mapIngredientEntity(ingredient)

        val savedIngredient = ingredientRepository.save(newIngredient)
        return savedIngredient.toIngredientDto()
    }

    private fun mapIngredientEntity(ingredientDto: IngredientCreationDto): Ingredient {
        return Ingredient(
            name = ingredientDto.name,
            description = ingredientDto.description,
            url = ingredientDto.url
        )
    }
}