package com.justfoodplease.app.justfoodplease.services.impl

import com.justfoodplease.app.justfoodplease.entities.MeasurementUnit
import com.justfoodplease.app.justfoodplease.entities.Recipe
import com.justfoodplease.app.justfoodplease.entities.RecipeIngredient
import com.justfoodplease.app.justfoodplease.exception.JfpException
import com.justfoodplease.app.justfoodplease.models.request.RecipeCreationDto
import com.justfoodplease.app.justfoodplease.models.request.RecipeIngredientCreationDto
import com.justfoodplease.app.justfoodplease.models.response.RecipeDto
import com.justfoodplease.app.justfoodplease.models.response.RecipeIngredientDto
import com.justfoodplease.app.justfoodplease.models.response.RecipesCollection
import com.justfoodplease.app.justfoodplease.repositories.AuthorRepository
import com.justfoodplease.app.justfoodplease.repositories.IngredientRepository
import com.justfoodplease.app.justfoodplease.repositories.MeasurementUnitRepository
import com.justfoodplease.app.justfoodplease.repositories.RecipeRepository
import com.justfoodplease.app.justfoodplease.services.RecipeService
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class RecipeServiceImpl(
    private val recipeRepository : RecipeRepository,
    private val ingredientRepository : IngredientRepository,
    private val authorRepository : AuthorRepository,
    private val unitRepository : MeasurementUnitRepository
) : RecipeService {

    private val logger: Logger = LogManager.getLogger(RecipeServiceImpl::class.java)

    /* Get all Recipes */
    override fun getAllRecipes(): RecipesCollection {
        val recipes = recipeRepository.findAll().map {
            it.toRecipeDto()
        }
        return RecipesCollection(
            collection = recipes
        )
    }

    override fun createRecipe(recipe: RecipeCreationDto): RecipeDto {
        try {
            val author = authorRepository.getById(recipe.authorId.toInt())

            val newRecipe = Recipe(
                    title = recipe.title,
                    subtitle = recipe.subtitle,
                    description = recipe.description,
                    ingredients = emptyList<RecipeIngredient>(),
                    instructions = recipe.instructions,
                    author = author,
                    minutes = recipe.minutes.toInt(),
                    serving = recipe.serving
            )

            val recipeIngredients = recipe.ingredients.map {
                mapRecipeIngredientEntity(it, newRecipe)
            }

            newRecipe.ingredients = recipeIngredients

            return recipeRepository.save(newRecipe).toRecipeDto()
        } catch(e: Exception) {
            throw JfpException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY)
        }
    }

    override fun updateRecipe(recipe: RecipeDto): RecipeDto {
        TODO("Not yet implemented")
    }

    private fun mapRecipeIngredientEntity(
        recipeIngredientDto : RecipeIngredientCreationDto,
        recipe: Recipe
    ) : RecipeIngredient {
        val ingredient = ingredientRepository.getById(recipeIngredientDto.ingredientId)
        // get the measurement unit if it exists
        val unit = recipeIngredientDto.measurementUnitId.let {
            unitRepository.getById(recipeIngredientDto.measurementUnitId)
        }

            return RecipeIngredient(
                ingredient = ingredient,
                quantity = recipeIngredientDto.quantity,
                measurementUnit = unit,
                recipe = recipe
            )
        }
}

