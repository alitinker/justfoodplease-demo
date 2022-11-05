package com.justfoodplease.app.justfoodplease.controllers

import com.justfoodplease.app.justfoodplease.models.request.RecipeCreationDto
import com.justfoodplease.app.justfoodplease.models.response.RecipeDto
import com.justfoodplease.app.justfoodplease.models.response.RecipesCollection
import com.justfoodplease.app.justfoodplease.services.RecipeService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/recipes")
@Validated
class RecipeController(
    private val recipeService: RecipeService
) {

    @GetMapping()
    fun getRecipes() : RecipesCollection {
        return recipeService.getAllRecipes()
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping()
    fun createRecipe(@RequestBody recipeDto : RecipeCreationDto) : RecipeDto = recipeService.createRecipe(recipeDto)


}